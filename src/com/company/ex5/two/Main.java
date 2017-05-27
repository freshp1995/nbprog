package com.company.ex5.two;

import java.util.concurrent.ExecutionException;

/**
 * Created by patricklanzinger on 26.05.17.
 */
public class Main {
    public static void main(String[] args) {
        int obj = 1;

        for (int i = 0; i < obj; i++) {
            new Thread(Main::calcPi).start();
        }
    }

    public static void calcPi() {

        long startTime = System.nanoTime();
        CalculatePartOfPiQuater pi = new CalculatePartOfPiQuater(1000, 1);
        try {
            pi.serialCalculation();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Double duration = (double) (System.nanoTime() - startTime) * 1e-6;

            System.out.println("Duration with instantiation time: " + duration);
        }
    }
}
