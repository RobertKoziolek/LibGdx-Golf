package com.robcio.golf.component.util;

import com.badlogic.ashley.core.Component;

public class RepeatingTimer implements Component {

    private final float resetTime;

    public float time;

    public Component component;

    private RepeatingTimer(final float time, final Component component) {
        this.resetTime = time;
        this.time = time;
        this.component = component;
    }

    public static RepeatingTimer of(final float time, final Component component) {
        return new RepeatingTimer(time, component);
    }

    public void reset() {
        this.time = resetTime;
    }
}
