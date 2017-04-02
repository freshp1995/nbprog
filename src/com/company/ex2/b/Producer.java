package com.company.ex2.b;

import jdk.nashorn.internal.objects.NativeUint8Array;

import java.util.Random;

/**
 * Created by patricklanzinger on 26.03.17.
 */
public class Producer implements Runnable {

    private int id;
    private Buffer buffer;
    private Produced consumer;

    public Producer() {
        buffer = new Buffer();
    }

    public void setConsumer(Produced consumer) {
        this.consumer = consumer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        Random random = new Random();
        int randomNumber = -1;

        int Low = 0;
        int High = 10;

        System.out.println("Create Numbers: " + getId());
        while (randomNumber != 0) {
            randomNumber = random.nextInt(High - Low) + Low;
            this.buffer.addNumber(randomNumber);
            this.consumer.empty(false, this.getId());
        }
        synchronized (this) {
            notifyAll();
        }
        System.out.println("Finished creating numbers: " + getId());
    }


    public synchronized Integer getValue() {
        System.out.println("Producer " + getId() + ": get value from consumer");
        if (!this.buffer.getBuffer().isEmpty()) {

            int temp = this.buffer.getBuffer().removeFirst();
            this.consumer.empty(false, this.getId());
            return temp;

        } else {
            return 0;
        }
    }
}
