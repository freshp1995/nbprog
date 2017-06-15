package com.company.ex6;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class ST implements Runnable {

    private ArrayList<MonitoringThread> mt;

    public ST(ArrayList<MonitoringThread> mt) {
        this.mt = mt;
    }

    @Override
    public void run() {
        
    }
}
