package com.company.ex3.one;

import java.util.Random;
import java.util.concurrent.locks.*;

/**
 * Created by patrick on 4/25/17.
 */
public class Database implements ReadWrite {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Random random;

    int[] data;
    int size = 0;

    public Database(int size) {
        this.size = size;
        this.data = new int[size];
        random = new Random();
    }

    @Override
    public void acquireRead() {
        lock.readLock().lock();
    }

    @Override
    public void releaseRead() {
        lock.readLock().unlock();
    }

    @Override
    public void acquireWrite() {
        lock.writeLock().lock();
    }

    @Override
    public void releaseWrite() {
        lock.writeLock().unlock();
    }

    public int readValue() {
        int temp = 0;
        try {
            this.acquireRead();
            System.out.println("read");
            temp = data[Math.abs(random.nextInt() % this.size)];
        } finally {
            this.releaseRead();
            return temp;
        }
    }

    public void writeValue() {

        try {
            this.acquireWrite();
            System.out.println("write");
            data[Math.abs(random.nextInt() % this.size)] = random.nextInt();
        } finally {
            this.releaseWrite();
        }
    }


}
