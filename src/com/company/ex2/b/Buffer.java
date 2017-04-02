package com.company.ex2.b;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by patricklanzinger on 26.03.17.
 */
public class Buffer {
    private LinkedList<Integer> buffer;

    public Buffer() {
        this.buffer = new LinkedList<>();
    }


    public LinkedList<Integer> getBuffer() {
        return buffer;
    }

    public void setBuffer(LinkedList<Integer> buffer) {
        this.buffer = buffer;
    }

    public void addNumber(Integer number) {
        buffer.add(number);
    }
}
