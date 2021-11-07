import java.util.concurrent.Semaphore;
/**
 *@author Daniel Dinelli
 *@Date 07/11/2021
 *@Licence GNU GPL
 */
/**
 * Follower Class is passed 4 of the 8 threads from main
 * name - holds the name of the current thread
 * followerQueue - allows access to FifoQueues methods
 * mutex - only allows one thread through the run method at a time
 * rendezvous - threads wait for each other after printing
 * followers - keeps track of how many threads are waiting in queue
 * mySema - hold semaphore value passed to this class
 * waitForFollower - used with waitForLeader to ensure both print together
 * printMutex - like mutex only lets one thread to print at a time
 */
public class Follower implements Runnable{
    private String name = "";
    public static FifoQueue followerQueue = new FifoQueue();
    Semaphore mutex = new Semaphore(1);
    static Semaphore rendezvous = new Semaphore(0);
    public static int followers = 0;
    Semaphore mySema;
    public static Semaphore waitForFollower = new Semaphore(0);
    static Semaphore printMutex = new Semaphore(1);

    /**
     * Constructor used to assign thread values
     * @param name
     * @param threadSema
     */
    public Follower(String name,Semaphore threadSema){
        this.name = name;
        mySema = threadSema;
    }

    /**
     * This method along with the leader version allow the name of each thread to print out together
     */
    @Override
    public void run() {
        try{
            mutex.acquire();
            if(Leader.leaders > 0){
                Leader.leaders--;
                Leader.leaderQueue.threadSignal();
            }
            else{
                followers++;
                mutex.release();
                followerQueue.threadWait(mySema);
            }
            printMutex.acquire();
            System.out.print(name);
            Leader.waitForLeader.release();
            waitForFollower.acquire();
            printMutex.release();

            rendezvous.release();

        }
        catch(Exception e){

        }
    }
}
