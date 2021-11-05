import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Leader implements Runnable{
    private String name = "";
    public static int followers, leaders = 0;
    FifoQueue queue = new FifoQueue();
    Semaphore mutex = new Semaphore(1);
    Semaphore rendezvous = new Semaphore(0);

    public Leader(String name,Semaphore threadSema){
        this.name = name;
        queue.threadWait(threadSema);
    }

    @Override
    public void run() {
        try{
            mutex.wait();
            if (followers > 0){
                followers--;
                //Follower.followerQueue.signal();
            }
            else{
                leaders++;
                mutex.release();
                //leaderQueue.wait();
            }
            //dance();
            rendezvous.wait();
            mutex.release();
        }
        catch(Exception e){

        }

    }
}
