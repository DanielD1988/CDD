import java.util.concurrent.Semaphore;
/**
 *@author Daniel Dinelli
 *@Date 10/11/2021
 *@Licence GNU GPL
 */
/**
 * Buffer holds an instance of the buffer queue
 * Event holds the tasks variable
 * mutex used to allow only one thread to proceed at once
 * unlockQueue allows access to add to buffer queue
 * producerFinFlag when the producer is finished this flag will be set to true
 * this is used to tell the consumer the producer is done
 */
public class Producer implements Runnable{
    public static Buffer buffer = new Buffer();
    private Event event;
    private Semaphore mutex = new Semaphore(1);
    public static Semaphore unlockQueue = new Semaphore(0);
    public static boolean producerFinFlag = false;

    public Producer(){

    }

    /**
     * This run method adds events to the buffer queue and continues producing until it reaches
     * a limit then breaks the loop
     */
    @Override
    public void run() {
        try{
            while(true){
                mutex.acquire();
                event = new Event();
                if(buffer.spacesFree() < 20){
                    unlockQueue.release();
                }
                else{
                    producerFinFlag = true;
                    break;
                }
                mutex.release();

                unlockQueue.acquire();
                buffer.addToBuffer(event);
            }
        }
        catch(Exception e){

        }
    }
}
