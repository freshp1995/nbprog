package com.company.ex4.one;

/**
 * Created by patricklanzinger on 12.05.17.
 */
public class ExpensiveObject {
    private int expensive = 0;

    private void addExpensive() {
        expensive++;
    }

    public int getExpensive() {
        this.addExpensive();
        return this.expensive;
    }
}
