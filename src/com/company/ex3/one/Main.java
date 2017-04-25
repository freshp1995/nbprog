package com.company.ex3.one;

import java.util.concurrent.*;

/**
 * Created by patrick on 4/25/17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Database database = new Database(1000);

        int accesses = 10000000;
        Thread t1 = new Thread(() -> {
            System.out.println("test started");
            for (int i = 0; i < accesses; i++) {
                database.readValue();
            }

            System.out.println("finished");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("test started");
            for (int i = 0; i < accesses; i++) {
                database.writeValue();
            }

            System.out.println("finished");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("all read and write accesses are made");


    }
}
