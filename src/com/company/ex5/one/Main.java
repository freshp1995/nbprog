package com.company.ex5.one;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by patricklanzinger on 26.05.17.
 */
public class Main {
    public int number = 1000;
    private NumberRange numberRange = new NumberRange(0, 40);

    public static void main(String[] args) {
        Main m = new Main();

        int temp = 1;

        switch (temp) {
            case 0:
                for (int i = 0; i < m.number; i++) {
                    new Thread(m::inRange).start();
                }

                new Thread(m::checkInRange).start();
                break;
            case 1:
                for (int i = 0; i < m.number; i++) {
                    new Thread(m::inRangeSync).start();
                }

                //new Thread(m::checkInRangeSync).start();
                break;

        }

    }

    public void inRange() {
        for (int i = 0; i < number; i++) {
            int lower = ThreadLocalRandom.current().nextInt(0, 20);
            int upper = ThreadLocalRandom.current().nextInt(21, 40);
            numberRange.setLower(lower);
            numberRange.setUpper(upper);

            //test if value is between upper and lower
            System.out.println(numberRange.isInRange(ThreadLocalRandom.current().nextInt(lower, upper)));
        }
    }

    public void checkInRange() {
        for (int i = 0; i < number; i++) {
            int value = ThreadLocalRandom.current().nextInt(0, 40);
            Boolean ret = numberRange.isInRange(value);
            //System.out.println(value + " : " + ret);
        }
    }

    public void inRangeSync() {
        int count = 0;
        for (int i = 0; i < number; i++) {
            int lower = ThreadLocalRandom.current().nextInt(0, 20);
            int upper = ThreadLocalRandom.current().nextInt(21, 40);
            numberRange.setLowerSync(lower);
            numberRange.setUpperSync(upper);

            //test if value is between upper and lower
            int val = ThreadLocalRandom.current().nextInt(lower, upper);
            Boolean temp = numberRange.isInRangeSync(val);


            if (!temp) {
                count++;
                System.out.println(temp + " : " + lower + " : " + upper + " : " + val);
            }
        }

        //System.out.println("Number of failed tests: " + count);
    }

    public void checkInRangeSync() {
        for (int i = 0; i < number; i++) {
            int value = ThreadLocalRandom.current().nextInt(0, 40);
            Boolean ret = numberRange.isInRangeSync(value);
            //System.out.println(value + " : " + ret);
        }
    }
}
