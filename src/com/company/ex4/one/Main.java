package com.company.ex4.one;

/**
 * Created by patricklanzinger on 12.05.17.
 */
public class Main {
    public LazyInitRaceCondition condition = new LazyInitRaceCondition();

    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        int i = 0;

        switch (i){
            case 0: {
                System.out.println("First:");
                new Thread(m::makeInstance, "Thread one ").start();
                new Thread(m::makeInstance, "Thread two ").start();
                new Thread(m::makeInstance, "Thread three").start();
                new Thread(m::makeInstance, "Thread four ").start();
                break;
            }

            case 1: {
                System.out.println("Second:");
                new Thread(m::makeInstanceTS1, "Thread one ").start();
                new Thread(m::makeInstanceTS1, "Thread two ").start();
                new Thread(m::makeInstanceTS1, "Thread three").start();
                new Thread(m::makeInstanceTS1, "Thread four ").start();
                break;
            }

            case 2: {
                System.out.println("Third:");
                new Thread(m::makeInstanceTS2, "Thread one ").start();
                new Thread(m::makeInstanceTS2, "Thread two ").start();
                new Thread(m::makeInstanceTS2, "Thread three").start();
                new Thread(m::makeInstanceTS2, "Thread four ").start();
                break;
            }
        }
    }

    public void makeInstance() {
        ExpensiveObject expensiveObject = condition.getInstance();

        System.out.println(Thread.currentThread().getName() + "\t: " + System.identityHashCode(expensiveObject));
    }

    public void makeInstanceTS1() {
        ExpensiveObject expensiveObject = condition.getInstanceTS1();

        System.out.println(Thread.currentThread().getName() + "\t: " + System.identityHashCode(expensiveObject));
    }

    public void makeInstanceTS2() {
        ExpensiveObject expensiveObject = condition.getInstanceTS2();

        System.out.println(Thread.currentThread().getName() + "\t: " + System.identityHashCode(expensiveObject));
    }
}
