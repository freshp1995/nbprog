package com.company.ex6.one;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class ST implements Runnable {

    private ArrayList<MonitoringThread> mt = new ArrayList<>();
    private Timer timer;

    public ST(Timer timer) {
        this.timer = timer;
    }

    public void init(int n, StopperThread spt, Integer info) {
        for (int i = 0; i < n; i++) {
            this.mt.add(new MT(spt, info));
        }
    }

    public ArrayList<MonitoringThread> getMt() {
        return this.mt;
    }

    @Override
    public void run() {
        for (MonitoringThread mt : this.mt) {
            new Thread(mt).start();
        }

        while (true) {
            if (this.timer.getValue()) {
                Boolean temp = false;
                for (MonitoringThread mt : this.mt) {
                    if (mt.getSleeping() && !mt.getWorking() && mt.getIntrusionCleared()) {
                        mt.setWorking(true);
                        mt.setSleeping(false);
                        temp = true;
                    }
                }

                if (temp) {
                    System.out.println("Start mts");
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
