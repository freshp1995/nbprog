package com.company.ex6.two;

import com.company.ex6.one.SPT;
import com.company.ex6.one.WorkerTimer;

import java.util.ArrayList;

/**
 * Created by patricklanzinger on 12.06.17.
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

        ST st = new ST(wtemp);
        SPT spt_east = new SPT(east);
        SPT spt_west = new SPT(west);
        SPT spt_south = new SPT(south);
        SPT spt_north = new SPT(north);

        ArrayList temp = new ArrayList<SPT>();
        temp.add(spt_east);
        temp.add(spt_west);
        temp.add(spt_south);
        temp.add(spt_north);

        st.init(1, temp);
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
