package com.company.ex3.three;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by patrick on 4/25/17.
 */
public class Main {

    public static int temp = 0;
    public static int accesses = 10000;
    public static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(Main::access);

        Thread t2 = new Thread(Main::access);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("all read and write accesses are made");

        System.out.println(temp + " : " + accesses * 2);
    }

    public static void access() {
        System.out.println("test started");
        for (int i = 0; i < accesses; i++) {
            try {
                semaphore.acquire();
                temp++;
            } finally {
                semaphore.release();
            }
        }

        System.out.println("finished");
    }
}
