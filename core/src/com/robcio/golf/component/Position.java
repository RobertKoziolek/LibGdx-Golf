package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Position implements Component {
    public float x = 0.0f;
    public float y = 0.0f;

    public static Position of(final float x, final float y){
        return new Position(x, y);
    }
}
