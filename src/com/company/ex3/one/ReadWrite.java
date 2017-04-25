package com.company.ex3.one;

/**
 * Created by patrick on 4/25/17.
 */
public interface ReadWrite {

    void acquireRead();  //befor read access
    void releaseRead();  //after read access
    void acquireWrite(); //befor write access
    void releaseWrite(); //after write access

}
