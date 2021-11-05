import java.util.concurrent.Semaphore;

public class Follower implements Runnable{
    private String name = "";
    FifoQueue queue = new FifoQueue();
    Semaphore mutex = new Semaphore(1);
    Semaphore rendezvous = new Semaphore(0);

    public Follower(String name,Semaphore threadSema){
        this.name = name;
        queue.threadWait(threadSema);
    }

    @Override
    public void run() {
        try{

            mutex.wait();
            if(Leader.leaders > 0){
                Leader.leaders--;
                //Leader.leaders.signal();
            }
            else{
                Leader.followers++;
                mutex.release();
                //followerQueue.wait();
            }
            System.out.println(name);
            rendezvous.release();

        }
        catch(Exception e){

        }
    }
}
