package com.company.ex6;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by patricklanzinger on 12.06.17.
 */
public class Main {
    private static ArrayList<Thread> mts = new ArrayList<>();

    private static int M = 1;
    private volatile static long time;
    private volatile static Boolean toggle = true;
    private volatile static Boolean interupt = false;

    public static void main(String[] args) {
        for (int i = 0; i < M; i++) {
            mts.add(new Thread(Main::MT));
        }

        new Thread(Main::ST).start();
        new Thread(Main::STP).start();
        while (true) {
            time = System.currentTimeMillis();
        }
    }

    public static void ST() {
        while (true) {
            if (((int)(time / 1000)) % 10 == 0 && toggle) {
                System.out.println("Start MT");
                interupt = false;
                for (int i = 0; i < M; i++) {
                    mts.get(i).start();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                toggle = !toggle;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void STP() {
        while (true) {
            if (((int) (time / 1000)) % 10 == 0 && !toggle) {
                System.out.println("Stop MT");
                interupt = true;
                for (int i = 0; i < M; i++) {
                    try {
                        mts.get(i).join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                toggle = !toggle;
            }
        }
    }

    public static void MT() {
        while (true) {
            System.out.println("Monitoring Thread: " + time);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (interupt) {
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
