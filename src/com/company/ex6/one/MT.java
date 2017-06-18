package com.company.ex6.one;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class MT implements MonitoringThread {

    private Boolean working = Boolean.FALSE;
    private Boolean sleeping = Boolean.TRUE;
    private Boolean intrusionCleared = Boolean.TRUE;
    private Random rnd = new Random();
    private StopperThread stopperThread;

    private Integer info;

    public MT(StopperThread stopperThread, Integer info) {
        this.stopperThread = stopperThread;
        this.info = info;
    }

    @Override
    public void run() {

        while (!working) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (true) {
            // thread sleeps for one second
            working = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // calculate probability of intrusion
            int temp = ThreadLocalRandom.current().nextInt(0, 10);
            if (temp > 2) {
                stopperThread.intrusion(this.info);
            } else {
                System.out.println("No Intrusion");
            }

            // wait for restart of thread
            while (working) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I am working");

            }

            while (sleeping) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleeping");
            }
        }
    }


    @Override
    public void setWorking(Boolean working) {
        synchronized (this.working) {
            this.working = working;
        }
    }

    @Override
    public void setSleeping(Boolean sleeping) {
        synchronized (this.sleeping) {
            this.sleeping = sleeping;
        }
    }

    @Override
    public Boolean getWorking() {
        synchronized (this.working) {
            return this.working;
        }
    }

    @Override
    public Boolean getSleeping() {
        synchronized (this.sleeping) {
            return this.sleeping;
        }
    }

    @Override
    public Boolean getIntrusionCleared() {
        synchronized (this.intrusionCleared) {
            return intrusionCleared;
        }
    }

    @Override
    public void setIntrusionCleared(Boolean intrusionCleared) {
        synchronized (this.intrusionCleared) {
            this.intrusionCleared = intrusionCleared;
        }
    }
}
