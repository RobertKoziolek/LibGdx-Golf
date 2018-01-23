package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Force implements Component {
    public float value = 0.0f;

    public static Force of(final float value) {
        return new Force(value);
    }
}
