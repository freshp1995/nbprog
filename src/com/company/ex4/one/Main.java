package com.company.ex4.one;

/**
 * Created by patricklanzinger on 12.05.17.
 */
public class Main {
    public static LazyInitRaceCondition condition = null;

    public static void main(String[] args) {
        new Thread(Main::makeInstance,"Thread one").start();
        new Thread(Main::makeInstance, "Thread two").start();
        new Thread(Main::makeInstance, "Thread three").start();
        new Thread(Main::makeInstance, "Thread four").start();
    }

    public static void makeInstance() {
        Main.condition = new LazyInitRaceCondition();
        ExpensiveObject expensiveObject = condition.getInstance();

        System.out.println(Thread.currentThread().getName() + " : " + System.identityHashCode(expensiveObject));
    }
}
