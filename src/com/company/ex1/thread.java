package com.company.ex1;

import java.util.Random;

/**
 * Created by patricklanzinger on 19.03.17.
 */
public class thread {
    public static void main(String[] args) {

        int i = 0;
        try {
            for (i = 0; i < 100000; i++) {

                final int threadNumber = i;

                Thread t = new Thread(() -> {
                    int j = 0;
                    while (true) {
                        try {
                            //System.out.println("tick n" + j++ + " thread " + threadNumber + " activeThreads " + Thread.activeCount());
                            Thread.sleep(new Random().nextInt(10000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                });
                t.start();

            }
        } catch (Throwable throwable) {
            System.out.println("### Maximum threads: " + String.format("%,d", i));
            System.out.println(throwable.toString());
        }
    }
}
