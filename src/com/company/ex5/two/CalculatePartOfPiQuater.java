package com.company.ex5.two;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Math.abs;

/**
 * Created by patricklanzinger on 26.05.17.
 */
public class CalculatePartOfPiQuater {
    private static int repetitions = 1900000;
    private static int iterations = 1000;

    private double calcPi() {
        Random rnd = new Random();

        int count = 0;

        for (int i = 0; i < iterations; i++) {
            double x_val = rnd.nextDouble();
            double y_val = rnd.nextDouble();

            if (Math.sqrt(x_val * x_val + y_val * y_val) <= 1)
                count++;
        }
        return count * 4.0 / iterations;
    }

    public void parallelCalculation(int nThreads) {
        Double errorSum = 0.0;

        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        List<Future<Double>> results = new ArrayList<>(repetitions);

        for (int i = 0; i < repetitions; i++) {

            Callable<Double> task = this::calcPi;
            results.add(executor.submit(task));
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < repetitions; i++) {
            try {
                BigDecimal pi = new BigDecimal(results.get(i).get());
                Double error = abs(Math.PI - pi.doubleValue());

                errorSum += error;

                sum = sum.add(pi);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        double durationMs = (double) (System.nanoTime() - startTime) * 1e-6;
        Double averageError = errorSum / repetitions;

        BigDecimal pi = sum.divide(new BigDecimal(repetitions), RoundingMode.HALF_UP);
        Double error = abs(Math.PI - pi.doubleValue());

        output(pi.toString(), error, durationMs);
    }

    private void output(String pi, Double error, Double time) {
        System.out.println("Pi: " + pi);
        System.out.println("error: " + String.format("%,6f", error));
        System.out.println("time consumed: " + String.format("%,3f", time));
    }

    private class Helper {

        Helper(double error, BigDecimal pi) {
            this.error = error;
            this.pi = pi;
        }

        private double error;
        private BigDecimal pi;

        public double getError() {
            return error;
        }

        public void setError(double error) {
            this.error = error;
        }

        public BigDecimal getPi() {
            return pi;
        }

        public void setPi(BigDecimal pi) {
            this.pi = pi;
        }
    }
}
