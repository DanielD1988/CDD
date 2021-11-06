import java.util.concurrent.Semaphore;


public class Follower implements Runnable{
    private String name = "";
    public static FifoQueue followerQueue = new FifoQueue();
    Semaphore mutex = new Semaphore(1);
    static Semaphore rendezvous = new Semaphore(0);
    public static int followers = 0;
    Semaphore mySema;
    public static Semaphore waitForFollower = new Semaphore(0);
    static Semaphore printMutex = new Semaphore(1);

    public Follower(String name,Semaphore threadSema){
        this.name = name;
        mySema = threadSema;
    }

    @Override
    public void run() {
        try{
            mutex.acquire();
            if(Leader.leaders > 0){
                Leader.leaders--;
                Leader.leaderQueue.threadSignal();
            }
            else{
                followers++;
                mutex.release();
                followerQueue.threadWait(mySema);
            }
            printMutex.acquire();
            System.out.print(name);
            Leader.waitForLeader.release();
            waitForFollower.acquire();
            printMutex.release();

            rendezvous.release();

        }
        catch(Exception e){

        }
    }
}
