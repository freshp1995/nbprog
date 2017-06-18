package com.company.ex6.one;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class SPT implements StopperThread {

    protected ArrayList<MonitoringThread> mt;
    private Timer timer;

    public SPT(Timer timer) {
        this.timer = timer;
    }

    public void addMt(ArrayList<MonitoringThread> mt) {
        this.mt = mt;
    }

    @Override
    public void run() {

        while (true) {

            // switch between start stop when timer has specific value
            if (!this.timer.getValue()) {

                // make all threads sleep
                this.setThreadsSleeping();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // this function will be called if an intrusion is detected
    @Override
    public void intrusion(Integer info) {
        System.out.println("Intrusion: " + info);
        this.setThreadsSleeping();
    }

    protected void setThreadsSleeping() {
        Boolean temp = false;
        for (MonitoringThread m : this.mt) {
            if (!m.getSleeping() && m.getWorking()) {
                m.setSleeping(true);
                m.setWorking(false);
                temp = true;
            }
        }

        if (temp) {
            System.out.println("stop mts");
        }
    }
}
