package com.company.ex6;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class SPT implements StopperThread {

    private ArrayList<MonitoringThread> mt;

    public SPT(ArrayList<MonitoringThread> mt) {
        this.mt = mt;
    }

    @Override
    public void run() {
        this.setThreadsSleeping();
    }

    @Override
    public void intrusion() {
        this.setThreadsSleeping();
    }

    private void setThreadsSleeping() {
        for (MonitoringThread m : this.mt) {
            m.setSleeping(true);

        }
    }
}
