package com.company.ex4.one;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by patricklanzinger on 12.05.17.
 */
public class LazyInitRaceCondition {

    private ExpensiveObject instance = null;
    private Lock lock = new ReentrantLock();

    //not threadsafe
    public ExpensiveObject getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new ExpensiveObject();
        }

        return this.instance;
    }

    public synchronized ExpensiveObject getInstanceTS1() {
        if (this.instance == null) {
            this.instance = new ExpensiveObject();
        }

        return this.instance;
    }

    public ExpensiveObject getInstanceTS2() {
        lock.lock();
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        lock.unlock();

        return this.instance;
    }

}
