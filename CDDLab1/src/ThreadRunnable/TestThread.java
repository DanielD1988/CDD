package ThreadRunnable;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import ThreadRunnable.RunnableDemo;

/**
 *
 * @author Daniel Dinelli
 * @Date   2021-10-04
 * @Licence GNU GPL
 *
 */
public class TestThread {
    /**
     * @param args
     */
    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start();
        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }

}
