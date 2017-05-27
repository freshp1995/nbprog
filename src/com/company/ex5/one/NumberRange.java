package com.company.ex5.one;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * NumberRange
 * <p/>
 * Number range class that does not sufficiently protect its invariants
 *
 * @author Brian Goetz and Tim Peierls
 */

public class NumberRange {
    // INVARIANT: lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    Lock lowerLock = new ReentrantLock();
    Lock upperLock = new ReentrantLock();

    public NumberRange(int lower, int upper) {
        this.setLower(lower);
        this.setUpper(upper);
    }

    public void setLower(int i) {
        if (i > upper.get())
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get())
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }

    public void setLowerSync(int i) {

        lowerLock.lock();
        // Warning -- unsafe check-then-act
        if (i > upper.get())
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        lower.set(i);
    }

    public void setUpperSync(int i) {
        // Warning -- unsafe check-then-act
        upperLock.lock();
        if (i < lower.get())
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        upper.set(i);
    }

    public boolean isInRangeSync(int i) {

        Boolean tmp = (i >= lower.get() && i <= upper.get());
        lowerLock.unlock();
        upperLock.unlock();
        return tmp;
    }
}


