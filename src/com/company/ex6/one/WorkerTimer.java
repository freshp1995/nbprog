package com.company.ex6.one;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public class WorkerTimer implements Timer {

    private volatile Boolean value = Boolean.TRUE;

    @Override
    public Boolean getValue() {
        synchronized (this.value) {
            return value;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (((int) (System.currentTimeMillis() / 1000)) % 10 == 0) {
                synchronized (this.value) {
                    this.value = !value;
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
