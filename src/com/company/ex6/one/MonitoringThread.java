package com.company.ex6.one;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public interface MonitoringThread extends Runnable {
    void setWorking(Boolean working);

    void setSleeping(Boolean sleeping);

    Boolean getWorking();

    Boolean getSleeping();

    Boolean getIntrusionCleared();

    void setIntrusionCleared(Boolean intrusionCleared);
}
