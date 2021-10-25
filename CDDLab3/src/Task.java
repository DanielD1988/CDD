/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 *@author Daniel Dinelli
 *@Date 25/10/2021
 *@Licence GNU GPL
 */

/**
 * This Class uses semaphores to create a thread barrier
 */
public class Task implements Runnable {
    private String name;// holds thread name
    private static Semaphore turnstile1 =  new Semaphore(0);//sets a barrier for all threads
    private static Semaphore turnstile2 =  new Semaphore(1);//holds threads after critical section until all threads are finished
    private static Semaphore lock =  new Semaphore(1);//locks and unlocks letting only one thread through certain parts
    private static int count = 0;//when this reaches the max number of threads it start letting threads into the critical section
    private static int numberOfThreads = 0;//holds the max number of threads

    /**
     * This constructor sets the current thread name and number of threads
     * @param task_1
     * @param totalThreads
     */
    public Task(String task_1,int totalThreads) {
        name=task_1;
        numberOfThreads = totalThreads;
    }

    /**
     * This method sets a barrier and waits for all threads to arrive before letting
     * them continue to the critical section after they access the critical section
     * another barrier is set to wait for all threads to finish then this barrier releases each thread.
     */
    public void run()
    {

            try{
                lock.acquire();
                count++;

                if(count == numberOfThreads){
                    turnstile2.acquire();
                    turnstile1.release();
                }

                lock.release();

                turnstile1.acquire();
                turnstile1.release();

                System.out.println("Thread name: "+name);

                lock.acquire();
                count--;
                if(count == 0){
                    turnstile1.acquire();
                    turnstile2.release();
                }
                lock.release();
                turnstile2.acquire();
                turnstile2.release();
            }
            catch (Exception e){

            }
    }
}
