package com.robcio.golf.component.util;

import com.badlogic.ashley.core.Component;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Timer implements Component {

    public boolean done;

    public float time;

    public final float startTime;

    public Component component;

    public static Timer of(final float time, final Component component) {
        return new Timer(false, time, time, component);
    }

}
