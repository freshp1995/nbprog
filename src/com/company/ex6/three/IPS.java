package com.company.ex6.three;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by patricklanzinger on 16.06.17.
 */
public class IPS {
    private ArrayList<Car> cars = new ArrayList<>();

    public IPS(int n) {
        for (int i = 0; i < n; i++) {
            this.cars.add(new Car());
        }
    }

    public Boolean checkIfCarsAreAvailable(int n) {
        int count = 0;
        for (Car c : this.cars) {
            if (c.getCurrent() == 0) {
                count++;
            }
        }

        return n <= count;
    }

    public void sendCars(int n, int direction) {
        ArrayList<Thread> cars = new ArrayList<>();

        if (checkIfCarsAreAvailable(n)) {
            for (Car c : this.cars) {
                c.setCurrent(direction);
                cars.add(new Thread(c));
            }

            //start threads
            for (Thread t : cars) {
                t.start();
            }

            //wait for them to finish
            for (Thread t : cars) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //set states
            for (Car c : this.cars) {
                c.setCurrent(0);
                c.setNext(0);
            }
            System.out.println("Intrusion cleared for: " + direction);

        } else {
            for (Car c : this.cars) {
                c.setNext(direction);
            }
        }
    }

    public Integer getNumberOfCars() {
        return this.cars.size();
    }
}
