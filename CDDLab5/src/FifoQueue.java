import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class FifoQueue {
    Semaphore mutex = new Semaphore(1);;
    public BlockingQueue<Semaphore> queue;

    public FifoQueue(){
        queue = new <Semaphore>ArrayBlockingQueue(4);
    }
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
