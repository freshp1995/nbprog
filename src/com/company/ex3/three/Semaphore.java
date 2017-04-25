package com.company.ex3.three;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by patrick on 4/25/17.
 */
public class Semaphore {
    final int capacity;
    int state = 0;
    Lock lock;
    Condition condition;

    public Semaphore(int c) {
        this.capacity = c;
        state = 0;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void acquire() {
        lock.lock();
        try {
            while (this.state == this.capacity) {
                this.condition.await();
            }
            state++;
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();

        try {
            state--;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
