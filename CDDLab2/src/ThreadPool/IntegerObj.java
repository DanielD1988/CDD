package ThreadPool;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
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
     * This constructor sets the value of val
     * @param val
     */
    IntegerObj(int val) {
        this.value = val;
    }

    /**
     * This method increments the integer value and returns the updated value
     * @return
     */
    int inc(){
        this.value++;
        return this.value;
    }
}
