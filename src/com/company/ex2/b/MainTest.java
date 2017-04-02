package com.company.ex2.b;

/**
 * Created by patricklanzinger on 26.03.17.
 */
public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        Producer p1 = new Producer();
        Producer p2 = new Producer();
        Producer p3 = new Producer();
        Producer p4 = new Producer();

        Consumer consumer = new Consumer();
        consumer.addProducer(p1);
        consumer.addProducer(p2);
        consumer.addProducer(p3);
        consumer.addProducer(p4);

        Thread c = new Thread(consumer);
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);

        c.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();


        t1.join();
        t2.join();
        t3.join();
        t4.join();
        c.join();
        System.out.println("all threads are back ending program");


    }
}
