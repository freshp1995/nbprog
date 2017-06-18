package com.company.ex6.one;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 12.06.17.
 */
public class Main {

    public static void main(String[] args) {

        //init everything
        WorkerTimer timer = new WorkerTimer(); //times the working hours
        ST st = new ST(timer); //the starter thread
        SPT spt = new SPT(timer); // the stopper thread

        st.init(1, spt, 0); //init the starter thread with a number of threads and and info about the threads
        spt.addMt(st.getMt());      // add the created MTs to the stopper threads

        //start the threads
        new Thread(timer).start();
        new Thread(st).start();
        new Thread(spt).start();
    }
}
