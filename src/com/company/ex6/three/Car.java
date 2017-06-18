package com.company.ex6.three;

/**
 * Created by patricklanzinger on 16.06.17.
 */
public class Car implements Runnable {
    private Integer current = 0;
    private Integer next = 0;

    public Integer getCurrent() {
        synchronized (this.current) {
            return current;
        }
    }

    public void setCurrent(Integer current) {
        synchronized (this.current) {
            this.current = current;
        }
    }

    public Integer getNext() {
        synchronized (this.next) {
            return next;
        }
    }

    public void setNext(Integer next) {
        synchronized (this.next) {
            this.next = next;
        }
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (this.getNext() != 0);
    }
}
