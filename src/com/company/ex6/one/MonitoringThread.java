package com.company.ex6;

/**
 * Created by patricklanzinger on 15.06.17.
 */
public interface MonitoringThread extends Runnable {
    void setNotWorking(Boolean working);
    void setSleeping(Boolean sleeping);
}
