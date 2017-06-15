package com.company.ex6.one;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class SPT implements StopperThread {

    private ArrayList<MonitoringThread> mt;
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
            if (!this.timer.getValue()) {
                this.setThreadsSleeping();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void intrusion() {
        this.setThreadsSleeping();
    }

    private void setThreadsSleeping() {
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
