package com.company.ex5.two;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Math.abs;

/**
 * Created by patricklanzinger on 26.05.17.
 */
public class CalculatePartOfPiQuater {
    private static int repetitions = 1900000;
    private int iterations = 1000;
    private int numberOfFuture = 100;
    private Random rnd = new Random();

    private static double serialDuration = 0;

    public CalculatePartOfPiQuater(int iterations, int numberOfFuture) {
        this.iterations = iterations;
        this.numberOfFuture = numberOfFuture;
    }

    private double pi() {
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

    private Helper repCalc() {
        BigDecimal sum = BigDecimal.ZERO;
        Double errorSum = 0.0;

        for (int i = 0; i < repetitions / numberOfFuture; i++) {
            BigDecimal pi = new BigDecimal(pi());
            Double error = abs(Math.PI - pi.doubleValue());

            errorSum += error;
            sum = sum.add(pi);
        }

        return new CalculatePartOfPiQuater.Helper(errorSum, sum);
    }

    private CalculatePartOfPiQuater.Helper makeAverage() throws ExecutionException, InterruptedException {

        BigDecimal sum = BigDecimal.ZERO;
        Double errorSum = 0.0;

        ArrayList<FutureTask<Helper>> tasks = new ArrayList<>();
        for (int i = 0; i < this.numberOfFuture; i++) {
            FutureTask temp = new FutureTask<Helper>(this::repCalc);
            new Thread(temp).start();
            tasks.add(temp);
        }

        System.out.println("started everything");
        for (int i = 0; i < numberOfFuture; i++) {

            CalculatePartOfPiQuater.Helper result = tasks.get(i).get();

            errorSum += result.getError();
            sum = sum.add(result.getPi());
        }

        BigDecimal pi = sum.divide(new BigDecimal(repetitions), RoundingMode.HALF_UP);
        Double averageError = errorSum / repetitions;
        Double error = abs(Math.PI - pi.doubleValue());

        return new CalculatePartOfPiQuater.Helper(error, pi);
    }


    public void serialCalculation() throws ExecutionException, InterruptedException {

        long startTime = System.nanoTime();

        CalculatePartOfPiQuater.Helper h = makeAverage();

        serialDuration = (double) (System.nanoTime() - startTime) * 1e-6;
        output(h.getPi().toString(), h.getError(), serialDuration);
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
