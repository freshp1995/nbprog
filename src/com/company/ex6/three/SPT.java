package com.company.ex6.three;

import com.company.ex6.one.MT;
import com.company.ex6.one.MonitoringThread;
import com.company.ex6.one.Timer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by patricklanzinger on 18.06.17.
 */
public class SPT extends com.company.ex6.one.SPT {
    private IPS ips;

    public SPT(Timer timer, IPS ips) {
        super(timer);
        this.ips = ips;
    }

    @Override
    public void intrusion(Integer info) {
        System.out.println("Intrusion: " + info);

        for (MonitoringThread m : this.mt) {
            m.setIntrusionCleared(false);
        }

        super.setThreadsSleeping();
        ips.sendCars(ThreadLocalRandom.current().nextInt(0, ips.getNumberOfCars()), info);
        for (MonitoringThread m : this.mt) {
            m.setIntrusionCleared(true);
        }
    }
}
