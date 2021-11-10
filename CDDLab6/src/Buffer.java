import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
/**
 *@author Daniel Dinelli
 *@Date 10/11/2021
 *@Licence GNU GPL
 */

/**
 * This class holds a queue of events
 * queue makes an instance of a blocking a queue
 * lock used to add to the queue
 */
public class Buffer {
    public BlockingQueue<Event> queue;
    private static Semaphore lock = new Semaphore(1);

    /**
     * Constructor creates an instance of an event blocking queue
     */
    public Buffer(){
        queue =  new LinkedBlockingDeque<>();
    }

    /**
     * This method adds to the event blocking queue
     * @param e
     */
    public void addToBuffer(Event e){
        try{
            lock.acquire();
            queue.add(e);
            lock.release();
        }
        catch(Exception exp){

        }

    }

    /**
     * This method removes the first event added to the queue
     * @return
     */
    public Event removeFromBuffer(){
        return queue.remove();
    }

    /**
     * This method returns the cureent size of the event queue
     * @return
     */
    public int spacesFree(){

        return queue.size();
    }

}
