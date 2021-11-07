import java.util.concurrent.Semaphore;
/**
 *@author Daniel Dinelli
 *@Date 07/11/2021
 *@Licence GNU GPL
 */
/**
 * Leader Class is passed 4 of the 8 threads from main
 * name - holds the name of the current thread
 * leaderQueue - allows access to FifoQueues methods
 * mutex - only allows one thread through the run method at a time
 * rendezvous - threads wait for each other after printing
 * leaders - keeps track of how many threads are waiting in queue
 * mySema - hold semaphore value passed to this class
 * waitForLeader - used with waitForFollower to ensure both print together
 * printMutex - like mutex only lets one thread to print at a time
 */
public class Leader implements Runnable{
    private String name = "";
    public static int leaders = 0;
    public static FifoQueue leaderQueue = new FifoQueue();
    Semaphore mutex = new Semaphore(1);
    Semaphore mySema;
    static Semaphore printMutex = new Semaphore(1);
    public static Semaphore waitForLeader = new Semaphore(0);

    /**
     * Constructor used to assign thread values
     * @param name
     * @param threadSema
     */
    public Leader(String name,Semaphore threadSema){
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
