package com.company.ex6.one;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 12.06.17.
 */
public class Main {

    public static void main(String[] args) {

        //init everything
        WorkerTimer timer = new WorkerTimer();
        ST st = new ST(timer);
        SPT spt = new SPT(timer);

        st.init(1, spt);
        spt.addMt(st.getMt());

        //start the threads
        new Thread(timer).start();
        new Thread(st).start();
        new Thread(spt).start();
    }
}
