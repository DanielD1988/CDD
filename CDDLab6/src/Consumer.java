import java.util.concurrent.Semaphore;
/**
 *@author Daniel Dinelli
 *@Date 10/11/2021
 *@Licence GNU GPL
 */

/**
 * The Consumer class removes from the event buffer queue
 * lock allows only one thread access to a section at a time
 * consume locked until there is something in the buffer queue
 */
public class Consumer implements Runnable{
    private static Semaphore lock = new Semaphore(1);
    private static Semaphore consume = new Semaphore(0);
    public Consumer (){

    }

    /**
     * This method consumes what is in the event buffer
     * it finish's when the buffer is zero and the producerFinFlag set to true
     */
    @Override
    public void run() {
        try{
            while(true){
                lock.acquire();

                if(Producer.buffer.spacesFree() != 0){
                    Event e = Producer.buffer.removeFromBuffer();
                    System.out.println("Consumed " + e.getProducedNumber());
                }
                else if(Producer.producerFinFlag == true && Producer.buffer.spacesFree() == 0 ){
                    break;
                }
                lock.release();
            }
        }
        catch(Exception e){

        }
    }
}
