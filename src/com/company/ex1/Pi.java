package com.company.ex1;

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
 * Created by patricklanzinger on 19.03.17.
 */
public class Pi {
    private static int iterations = 1000;
    private static int repetitions = 1900000;

    private static double serialDuration;

    public static void main(String[] args) {
        Pi p = new Pi();
        p.serialCalculation();

        p.parallelCalculation(1);
        p.parallelCalculation(2);
        p.parallelCalculation(4);
        p.parallelCalculation(20);
    }

    private double calcPi() {
        Random rnd = new Random();

        int count = 0;

        for (int i = 0; i < iterations; i++) {
            double x = rnd.nextDouble();
            double y = rnd.nextDouble();

            if (Math.sqrt(x * x + y * y) <= 1)
                count++;

        }
        return count * 4.0 / iterations;
    }

    private Helper makeAverage() {

        BigDecimal sum = BigDecimal.ZERO;
        Double errorSum = 0.0;

        for (int i = 0; i < repetitions; i++) {
            BigDecimal pi = new BigDecimal(calcPi());
            Double error = abs(Math.PI - pi.doubleValue());

            errorSum += error;
            sum = sum.add(pi);
        }

        BigDecimal pi = sum.divide(new BigDecimal(repetitions), RoundingMode.HALF_UP);
        Double averageError = errorSum / repetitions;
        Double error = abs(Math.PI - pi.doubleValue());

        return new Helper(error, pi);
    }

    private void output(String pi, Double error, Double time) {
        System.out.println("Pi: " + pi);
        System.out.println("error: " + String.format("%,6f", error));
        System.out.println("time consumed: " + String.format("%,3f", time));
    }

    public void serialCalculation() {

        long startTime = System.nanoTime();

        Helper h = makeAverage();

        serialDuration = (double) (System.nanoTime() - startTime) * 1e-6;
        output(h.getPi().toString(), h.getError(), serialDuration);
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
