package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Dimension implements Component {
    public float width = 0.0f;
    public float height = 0.0f;

    public static Dimension of(final float x, final float y) {
        return new Dimension(x, y);
    }

    public static Dimension of(final float r) {
        return new Dimension(r, r);
    }

    public boolean isSquare() {
        return (Math.abs(width - height) < 0.005f);
    }
}
