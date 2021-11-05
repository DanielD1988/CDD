/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *@author Daniel Dinelli
 *@Date 25/10/2021
 *@Licence GNU GPL
 */
public class Main {

    // Maximum number of threads in thread pool
    static final int MAX_T = 8;

    public static void main(String[] args)
    {

        // creates five tasks pass max number of threads
        Runnable r1 = new Leader("a",MAX_T);
        Runnable r2 = new Leader("b",MAX_T);
        Runnable r3 = new Leader("c",MAX_T);
        Runnable r4 = new Leader("d",MAX_T);
        Runnable r5 = new Follower("1",MAX_T);
        Runnable r6 = new Follower("2",MAX_T);
        Runnable r7 = new Follower("3",MAX_T);
        Runnable r8 = new Follower("4",MAX_T);

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);
        pool.execute(r6);
        pool.execute(r7);
        pool.execute(r8);

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