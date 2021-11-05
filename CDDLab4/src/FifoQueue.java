import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class FifoQueue {
    Semaphore mutex;
    public static BlockingQueue<Semaphore> queue;;

    public FifoQueue(){
        queue = new <Semaphore>ArrayBlockingQueue(4);
        mutex = new Semaphore(1);
    }
    public void threadWait(Semaphore mySem){
        try{
            mutex.acquire();
            queue.add(mySem);
            mutex.release();
            mySem.acquire();
        }
        catch(Exception e){

        }
    }

    public void threadSignal(){
        try{
            mutex.acquire();
            Semaphore sem = queue.remove();
            mutex.release();
            sem.release();
        }
        catch(Exception e){

        }
    }

}
