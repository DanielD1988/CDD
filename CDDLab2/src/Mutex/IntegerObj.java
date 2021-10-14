package Mutex;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.Semaphore;

/**
 * @author Daniel Dinelli
 * @Date 11/10/2021
 * @Licence GNU GPL
 */
/**
 * This class allows the user to increment the value integer
 */
class IntegerObj {
    int value;
    /**
     * Creates a Semaphore with the given number of permits
     * the initial number of permits available
     * This value may be negative, in which case releases must occur before any acquires will be granted
     */
    private Semaphore mutex = new Semaphore(1);

    /**
     * Constructor assigns int value
     * @param val
     */
    IntegerObj(int val) {
        this.value = val;
    }

    /**
     * This method increments the value everytime it is called
     * The Semaphore objects methods acquire and release
     * The acquire method gets the permit from the Semaphore and only allows one thread to access the variable at one time
     * The release method release the permit back to the Semaphore so the next thread can access the variable
     * @return
     */
    int inc(){
        try {
            mutex.acquire();
            this.value++;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            mutex.release();
            return this.value;
        }
    }
}

