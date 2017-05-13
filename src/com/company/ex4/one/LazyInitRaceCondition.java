package com.company.ex4.one;

/**
 * Created by patricklanzinger on 12.05.17.
 */
public class LazyInitRaceCondition {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }

        return this.instance;
    }
}
