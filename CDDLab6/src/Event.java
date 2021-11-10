import java.util.concurrent.ThreadLocalRandom;
/**
 *@author Daniel Dinelli
 *@Date 10/11/2021
 *@Licence GNU GPL
 */

/**
 * This class sets a variable for thread
 * threadNumber will be different for each thread
 */
public class Event {
    private static int threadNumber = 0;

    /**
     * This constructor inc the threadNumber each time the its called
     */
    public Event(){
            System.out.println("Produced " + threadNumber);
            threadNumber++;
    }

    /**
     * This method returns the the threadNumber value but dec before it returns
     * @return
     */
    public int getProducedNumber(){

        return threadNumber--;
    }
}
