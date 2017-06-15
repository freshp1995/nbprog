package com.company.ex6;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class MT implements MonitoringThread {

    private Boolean working = Boolean.TRUE;
    private Boolean sleeping = Boolean.TRUE;
    private Random rnd = new Random();
    private StopperThread stopperThread;

    public MT(StopperThread stopperThread) {
        this.stopperThread = stopperThread;
    }

    @Override
    public void run() {

        while (true) {
            // thread sleeps for one second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // calculate probability of intrusion
            int temp = ThreadLocalRandom.current().nextInt(0, 10);
            if (temp > 2) {
                System.out.printf("no intrusion");
                stopperThread.intrusion();
            } else {
                System.out.printf("intrusion");
            }

            // wait for restart of thread
            while (working) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (working) {
                    System.out.println("I am working");
                }
            }

            while (sleeping) try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void setNotWorking(Boolean working) {
        this.working = working;
    }

    @Override
    public void setSleeping(Boolean sleeping) {
        this.sleeping = sleeping;
    }
}
