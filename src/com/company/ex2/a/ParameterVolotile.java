package com.company.ex2.a;

/**
 * Created by patricklanzinger on 26.03.17.
 */
public class ParameterVolotile {
    private volatile int parameter = 0;

    public int getParameterValue() {
        return parameter;
    }

    public void setParameterValue(int parameter) {
        this.parameter = parameter;
    }

    public void addParameterValue() {
        this.parameter += 1;
    }
}
