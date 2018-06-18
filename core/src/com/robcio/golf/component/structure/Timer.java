package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

//TODO pomysly: dispenser wyrzucajacy to co u gory
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Timer implements Component {

    public float value;

    public Component component;

    public static Timer of(final float value, final Component component) {
        return new Timer(value, component);
    }
}
