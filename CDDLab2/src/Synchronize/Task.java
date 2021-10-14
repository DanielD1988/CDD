package Synchronize;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Daniel Dinelli
 * @Date 11/10/2021
 * @Licence GNU GPL
 */
public class Task implements Runnable {
    /**
     * The String name is the thread name
     * The IntegerObj total holds a reference to an integer object
     */
    private String name;
    private IntegerObj total;

    /**
     * Constructor assigns thread name to name and integer to total
     * @param task_1
     * @param total
     */
    public Task(String task_1, IntegerObj total) {
        name=task_1;
        this.total = total;
    }
    /**
     * Different threads access the inc method inside the run method to update the integer variable
     */
    public void run()
    {
        try
        {
            for (int i = 0; i<500; i++)
            {
                total.inc();
                if (i%100==0){
                    Thread.sleep(100);
                }

            }
            System.out.println(name+" complete");
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

