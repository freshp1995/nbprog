package com.company.ex2.a;

/**
 * Created by patricklanzinger on 26.03.17.
 */
public class ParameterVolotileSynchronized {
    private volatile int parameter = 0;

    public synchronized int getParameterValue() {
        return parameter;
    }

    public synchronized void setParameterValue(int parameter) {
        this.parameter = parameter;
    }

    public synchronized void addParameterValue() {
        this.parameter += 1;
    }
}
