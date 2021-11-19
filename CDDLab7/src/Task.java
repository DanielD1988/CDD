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
 *@Date 19/11/2021
 *@Licence GNU GPL
 */

/**
 * This class Task solves the dinning philosophers problem
 * footman limits the amount of threads taking forks
 * LeftRightfork sets which fork the thread can pick up
 * threadValue the number value given to each thread for an id
 * j used to give LeftRightfork its values
 * count used to release all threads when finished eating
 * mutex used for locking the j variable from being incremented by more than one thread at a time
 * mutex2 used for locking the count variable from being incremented by more than one thread at a time
 * afterEating holds all threads until all are finished eating
 * fork holds the list of available forks
 */
public class Task implements Runnable {
    private static Semaphore footman  = new Semaphore(4);
    int LeftRightfork;
    int threadValue;
    static int j = 0;
    static int count = 0;
    private static Semaphore mutex  = new Semaphore(1);
    private static Semaphore mutex2  = new Semaphore(1);
    private static Semaphore afterEating  = new Semaphore(0);
    private static Semaphore [] fork = new Semaphore [] {
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1)
    };
    public Task(int threadValue) {
        this.threadValue = threadValue;
    }
    public void run()
    {
        try{
            while(true){

                mutex.acquire();
                if(j < 5){
                    LeftRightfork = j;
                    j++;
                }
                else{
                    j = 0;
                    LeftRightfork = j;
                }
                mutex.release();

                get_forks(LeftRightfork);
                System.out.println(threadValue + " is eating");
                drop_forks(LeftRightfork);

                if(count == 4){

                    afterEating.release();

                }
                else{
                    mutex2.acquire();
                    ++count;
                    mutex2.release();
                }

                afterEating.acquire();
                afterEating.release();
                break;
            }


        }

        catch (Exception e){

        }
    }

    /**
     * Thread checks fork array for an available left then right fork
     * J is the left while j+1 is the right fork
     * if both are free then eat
     * @param j
     */
    public void get_forks(int j){
        try{
            footman.acquire();
            fork[j].acquire();
            fork[j+1].acquire();
        }
        catch(Exception e){

        }

    }

    /**
     * After eating release the forks for the next thread
     * release with the left fork then the right fork
     * @param j
     */
    public void drop_forks(int j) {
        try {
            fork[j].release();
            fork[j+1].release();
            footman.release();
        } catch (Exception e) {

        }
    }
}


