import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
/**
 *@author Daniel Dinelli
 *@Date 07/11/2021
 *@Licence GNU GPL
 */

/**
 * The class FifoQueue allows the Leader and Follower to use a queue
 * The queue is set as a Java blocking queue
 * mutex used in each of the methods for allowing one thread through at a time
 */
public class FifoQueue {
    Semaphore mutex = new Semaphore(1);;
    public BlockingQueue<Semaphore> queue;

    /**
     * Constructor
     * Used to make an instance blocking queue which can hold 4 semaphores
     */
    public FifoQueue(){
        queue = new <Semaphore>ArrayBlockingQueue(4);
    }

    /**
     * The threadWait method when called adds the current threads semaphore
     * into the queue and calls acquire on the semaphore to block it
     * This method takes the current threads semaphore
     * @param mySema
     */
    public void threadWait(Semaphore mySema){
        try{
            mutex.acquire();
            queue.add(mySema);
            mutex.release();
            mySema.acquire();
        }
        catch(Exception e){

        }
    }

    /**
     * The threadSignal method when called removes the current thread off the queue
     * and releases it from its blocking state
     */
    public void threadSignal(){
        try{
            mutex.acquire();
            Semaphore sema = queue.remove();
            mutex.release();
            sema.release();
        }
        catch(Exception e){

        }
    }

}
