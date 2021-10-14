package Synchronize;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
     * Constructor assigns int value
     * @param val
     *
     */
    IntegerObj(int val) {
        this.value = val;
    }

    /**
     * This method increments the value everytime it is called
     * The synchronized only allows one thread to increment the variable at one time
     * @return
     */
    synchronized int inc(){
        this.value++;
        return this.value;
    }
}

