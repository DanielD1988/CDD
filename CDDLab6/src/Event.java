import java.util.concurrent.Semaphore;

/**
 *@author Daniel Dinelli
 *@Date 10/11/2021
 *@Licence GNU GPL
 */

/**
 * This class sets a variable for thread
 * threadNumber will be different for each thread
 * lockVar limits the access to the variable to be inc
 */
public class Event {
    private int producerNum = 0;
    private static Semaphore lockVar = new Semaphore(1);

    /**
     * This constructor takes a integer to store for each time something is produced
     * @param producerNum
     */
    public Event(int producerNum){
            try{
                lockVar.acquire();
                this.producerNum = producerNum;
                System.out.println("Produced " + producerNum);
                lockVar.release();
            }
            catch(Exception e){

            }
    }

    /**
     * This method returns the the producerNum value for that each event created
     * @return
     */
    public int getProducedNumber(){

        return producerNum;
    }
}
