import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Leader implements Runnable{
    private String name = "";
    public static int leaders = 0;
    public static FifoQueue leaderQueue = new FifoQueue();
    Semaphore mutex = new Semaphore(1);
    Semaphore mySema;
    static Semaphore printMutex = new Semaphore(1);
    public static Semaphore waitForLeader = new Semaphore(0);

    public Leader(String name,Semaphore threadSema){
        this.name = name;
        mySema = threadSema;
    }

    @Override
    public void run() {
        try{

            mutex.acquire();
            if (Follower.followers > 0){
                Follower.followers--;
                Follower.followerQueue.threadSignal();
            }
            else{
                leaders++;
                mutex.release();
                leaderQueue.threadWait(mySema);
            }
            printMutex.acquire();
            System.out.print(name);
            Follower.waitForFollower.release();
            waitForLeader.acquire();
            System.out.println();
            printMutex.release();


            Follower.rendezvous.acquire();
            mutex.release();
        }
        catch(Exception e){

        }

    }
}
