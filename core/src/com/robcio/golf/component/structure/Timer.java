package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Timer implements Component {

    public float time;

    public Component component;

    public static Timer of(final float time, final Component component) {
        return new Timer(time, component);
    }
}
