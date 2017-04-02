package com.company.ex2.b;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by patricklanzinger on 26.03.17.
 */
public class Consumer implements Runnable, Produced {

    private ArrayList<Producer> producer = new ArrayList<>();
    private ArrayList<Boolean> empty = new ArrayList<>();
    private ArrayList<Producer> ended = new ArrayList<>();

    public void addProducer(Producer producer) {
        producer.setConsumer(this);
        producer.setId(this.producer.size());
        this.producer.add(producer);
        this.empty.add(true);
    }

    @Override
    public void run() {
        while (true) {

            if (this.empty.contains(true)) {
                try {
                    System.out.println("Wait till all buffers have numbers");
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Get Numbers");
            for (Iterator<Producer> it = this.producer.iterator(); it.hasNext(); ) {
                Producer produ = it.next();
                int temp = produ.getValue();
                if (temp != 0) {
                    System.out.print(temp + " " + "\n");
                } else {
                    it.remove();
                }
            }


            if (this.producer.isEmpty()) {
                return;
            }

        }
    }


    @Override
    public void empty(Boolean empty, int id) {
        this.empty.set(id, empty);
        if (!this.empty.contains(true)) {
            synchronized (this) {
                notifyAll();
            }
        }
    }
}
