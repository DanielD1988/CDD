/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

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
         * Creates a new AtomicInteger instance and sets the value to 0
         */
        private AtomicInteger counter = new AtomicInteger(0);

        /**
         * Constructor sets AtomicInteger value
         * @param val
         */
        IntegerObj(int val) {

                counter.set(val);
        }
        /**
         * This method increments the value everytime it is called
         * This inc method adds one to the AtomicInteger counter
         * and sets the value variable to the integer counter value
         * @return
         */
        void inc(){
                counter.incrementAndGet();
                this.value = counter.intValue();
        }
}

