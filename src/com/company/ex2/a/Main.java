package com.company.ex2.a;


/**
 * Created by patricklanzinger on 26.03.17.
 */
public class Main {

    int repetisions = 10;
    Parameter parameter = new Parameter();
    ParameterVolotile parameterVolotile = new ParameterVolotile();
    ParameterSynchronized parameterSynchronized = new ParameterSynchronized();
    ParameterVolotileSynchronized parameterVolotileSynchronized = new ParameterVolotileSynchronized();

    public static void main(String[] args) throws InterruptedException {
        String temp;
        String choice = "d";

        if (choice.equals("a")) {
            Main m = new Main();
            for (int i = 0; i < m.repetisions; i++) {
                Thread t1 = new Thread(m::thread1a);
                Thread t2 = new Thread(m::thread2a);

                t1.start();
                t2.start();

                t1.join();
                t2.join();


                System.out.println("After execution: " + m.parameter.getParameterValue());


            }
        } else if (choice.equals("b")) {
            Main m = new Main();
            for (int i = 0; i < m.repetisions; i++) {
                Thread t1 = new Thread(m::thread1b);
                Thread t2 = new Thread(m::thread2b);

                t1.start();
                t2.start();

                t1.join();
                t2.join();


                System.out.println("After execution: " + m.parameterVolotile.getParameterValue());


            }
        } else if (choice.equals("c")) {
            Main m = new Main();
            for (int i = 0; i < m.repetisions; i++) {
                Thread t1 = new Thread(m::thread1c);
                Thread t2 = new Thread(m::thread2c);

                t1.start();
                t2.start();

                t1.join();
                t2.join();


                System.out.println("After execution: " + m.parameterVolotile.getParameterValue());
                m.parameterVolotile.setParameterValue(0);


            }
        } else if (choice.equals("d")) {

            System.out.println("a synchronized: ");
            Main m = new Main();
            for (int i = 0; i < m.repetisions; i++) {
                Thread t1 = new Thread(m::thread1da);
                Thread t2 = new Thread(m::thread2da);

                t1.start();
                t2.start();

                t1.join();
                t2.join();


                System.out.println("After execution: " + m.parameterSynchronized.getParameterValue());

            }

            System.out.println("b synchronized: ");
            m = new Main();
            for (int i = 0; i < m.repetisions; i++) {
                Thread t1 = new Thread(m::thread1db);
                Thread t2 = new Thread(m::thread2db);

                t1.start();
                t2.start();

                t1.join();
                t2.join();


                System.out.println("After execution: " + m.parameterVolotileSynchronized.getParameterValue());

            }

            System.out.println("c synchronized: ");
            m = new Main();
            for (int i = 0; i < m.repetisions; i++) {
                Thread t1 = new Thread(m::thread1dc);
                Thread t2 = new Thread(m::thread2dc);

                t1.start();
                t2.start();

                t1.join();
                t2.join();


                System.out.println("After execution: " + m.parameterVolotileSynchronized.getParameterValue());
                m.parameterVolotileSynchronized.setParameterValue(0);

            }
        }

    }

    private void thread1a() {
        parameter.setParameterValue(3);
        System.out.println(parameter.getParameterValue());
    }

    private void thread2a() {
        parameter.setParameterValue(2);
    }

    private void thread1b() {
        parameterVolotile.setParameterValue(3);
        System.out.println(parameterVolotile.getParameterValue());
    }

    private void thread2b() {
        parameterVolotile.setParameterValue(2);
    }

    private void thread1c() {
        parameterVolotile.addParameterValue();
        System.out.println(parameterVolotile.getParameterValue());
    }

    private void thread2c() {
        parameterVolotile.addParameterValue();
    }

    private void thread1da() {
        parameterSynchronized.setParameterValue(3);
        System.out.println(parameterSynchronized.getParameterValue());
    }

    private void thread2da() {
        parameterSynchronized.setParameterValue(2);
    }

    private void thread1db() {
        parameterVolotileSynchronized.setParameterValue(3);
        System.out.println(parameterVolotileSynchronized.getParameterValue());
    }

    private void thread2db() {
        parameterVolotileSynchronized.setParameterValue(2);
    }

    private void thread1dc() {
        parameterVolotileSynchronized.addParameterValue();
        System.out.println(parameterVolotileSynchronized.getParameterValue());
    }

    private void thread2dc() {
        parameterVolotileSynchronized.addParameterValue();
    }
}
