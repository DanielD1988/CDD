import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Leader implements Runnable{
    private static Semaphore turnstile = new Semaphore(0);
    private Semaphore nextMutexLock = new Semaphore(0);
    public static Semaphore waitForFollower = new Semaphore(0);
    static Semaphore mutexLock = new Semaphore(1);
    private String name = "";
    static int numOfThreads = 0;
    public static BlockingQueue <String> leaderQueue;

    public Leader(String name,int totalThreads){
        this.name = name;
        leaderQueue = new<String> ArrayBlockingQueue(totalThreads / 2 );
        numOfThreads = totalThreads / 2;
    }

    @Override
    public void run() {
        try{
            //add thread names to queue
            mutexLock.acquire();
            leaderQueue.add(name);
            mutexLock.release();

            //allow only one thread out at a time
            if(leaderQueue.size() == numOfThreads){
                turnstile.release();
                numOfThreads--;
            }
            //block until all threads are in queue
            turnstile.acquire();
            name = leaderQueue.remove();

            //wait for both threads to reach same point
            Leader.waitForFollower.release();
            waitForFollower.acquire();

            //System.out.print(name);

            //allow another thread to go
            turnstile.release();

        }
        catch(Exception e){

        }

    }
    //attempt at my own blocking queue
    /*System.out.print(name);
            assignSemaphore = new Semaphore(0);
            leaderQ.add(assignSemaphore);
            mutexLock.release();
            if(leaderQ.size() == 4){
                currentSemaphore = popLeader();
                currentSemaphore.release();
                System.out.println(name);
            }
            System.out.println("here");

            assignSemaphore.acquire();
    public synchronized Semaphore popLeader(){
        Semaphore leader = leaderQ.remove();
        return leader;
    }*/
}
