/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *@author Daniel Dinelli
 *@Date 10/11/2021
 *@Licence GNU GPL
 */

/**
 * Make a thread for the Producer and Consumer and add them to the thread pool
 */
public class Main {

    // Maximum number of threads in thread pool
    static final int MAX_T = 5;

    public static void main(String[] args)
    {
        // creates eight tasks pass max a semaphore
        Runnable r1 = new Task(1);
        Runnable r2 = new Task(2);
        Runnable r3 = new Task(3);
        Runnable r4 = new Task(4);
        Runnable r5 = new Task(5);


        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // pool shutdown ( Step 4)
        pool.shutdown();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("total is: "+total.value);
    }
}
