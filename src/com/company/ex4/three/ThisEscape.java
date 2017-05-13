package com.company.ex4.three;

import java.awt.*;
import java.util.EventListener;

/**
 * Created by patricklanzinger on 12.05.17.
 */
public class ThisEscape {

    private static EventListener createListener() {
        return new EventListener() {
            public void onEvent(Event e) {
                //dosomething
            }
        };
    }

    public ThisEscape(EventSource source) {
        source.registerListener(ThisEscape.createListener());
    }
}
