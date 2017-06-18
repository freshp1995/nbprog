package com.company.ex6.two;

import com.company.ex6.one.*;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class ST implements Runnable {

    private ArrayList<ArrayList<MonitoringThread>> mt = new ArrayList<>();
    private ArrayList<WorkerTimer> timer;

    public ST(ArrayList<WorkerTimer> timer) {
        this.timer = timer;
    }

    public void init(int n, ArrayList<StopperThread> spt, ArrayList<Integer> infos) {

        int count = 0;
        ArrayList<MonitoringThread> temp;
        for (StopperThread s : spt) {
            temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new MT(s, infos.get(count)));
            }
            mt.add(temp);
            count++;
        }
    }

    public ArrayList<ArrayList<MonitoringThread>> getMt() {
        return this.mt;
    }

    @Override
    public void run() {
        for (ArrayList<MonitoringThread> m : this.mt) {
            for (MonitoringThread mt : m) {
                new Thread(mt).start();
            }
        }

        while (true) {

            for (int i = 0; i < 4; i++) {
                if (timer.get(i).getValue()) {
                    Boolean temp = false;
                    for (MonitoringThread mt : this.mt.get(i)) {
                        if (mt.getSleeping() && !mt.getWorking()) {
                            mt.setSleeping(false);
                            mt.setWorking(true);
                            temp = true;

                        }
                    }

                    if (temp) {
                        System.out.println("Start mts");
                    }
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
