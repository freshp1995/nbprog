package com.company.ex6.three;

import com.company.ex6.one.WorkerTimer;
import com.company.ex6.two.ST;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by patricklanzinger on 16.06.17.
 */
public class Main {

    public static void main(String[] args) {

        //init everything
        WorkerTimer east = new WorkerTimer();
        WorkerTimer west = new WorkerTimer();
        WorkerTimer south = new WorkerTimer();
        WorkerTimer north = new WorkerTimer();
        ArrayList<WorkerTimer> wtemp = new ArrayList<>();
        wtemp.add(west);
        wtemp.add(east);
        wtemp.add(south);
        wtemp.add(north);

        IPS ips = new IPS(ThreadLocalRandom.current().nextInt(1, 10));
        ST st = new ST(wtemp);
        SPT spt_east = new SPT(east, ips);
        SPT spt_west = new SPT(west, ips);
        SPT spt_south = new SPT(south, ips);
        SPT spt_north = new SPT(north, ips);

        ArrayList temp = new ArrayList<SPT>();
        temp.add(spt_east);
        temp.add(spt_west);
        temp.add(spt_south);
        temp.add(spt_north);

        ArrayList<Integer> names = new ArrayList<>();
        names.add(2);
        names.add(3);
        names.add(4);
        names.add(1);

        //number of MT threads for each direction
        st.init(1, temp, names);
        spt_east.addMt(st.getMt().get(0));
        spt_west.addMt(st.getMt().get(1));
        spt_south.addMt(st.getMt().get(2));
        spt_north.addMt(st.getMt().get(3));

        //start the threads
        new Thread(east).start();
        new Thread(west).start();
        new Thread(south).start();
        new Thread(north).start();

        new Thread(st).start();

        new Thread(spt_east).start();
        new Thread(spt_west).start();
        new Thread(spt_south).start();
        new Thread(spt_north).start();

    }
}
