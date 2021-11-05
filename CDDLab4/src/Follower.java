import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Follower implements Runnable{
    private static Semaphore turnstile = new Semaphore(0);
    private static Semaphore waitForLeader = new Semaphore(0);
    static Semaphore mutexLock = new Semaphore(1);
    private String name = "";
    public static BlockingQueue<String> followerQueue;
    static int numOfThreads = 0;

    public Follower(String name,int totalThreads){
        this.name = name;
        followerQueue = new <String> ArrayBlockingQueue(totalThreads / 2);
        numOfThreads = totalThreads / 2;
    }

    @Override
    public void run() {
        try{
            //add thread names to queue
            mutexLock.acquire();
            followerQueue.add(name);
            mutexLock.release();
            System.out.println(followerQueue.size());
            //allow only one thread out at a time

            turnstile.acquire();
            /*
            //block until all threads are in queue
            turnstile.acquire();
            name = followerQueue.remove();
            System.out.println(name);

            //wait for both threads to reach same point
            Leader.waitForFollower.release();
            waitForLeader.acquire();

            System.out.print(name);

            //allow another thread to go
            turnstile.release();*/

        }
        catch(Exception e){

        }
    }
}
