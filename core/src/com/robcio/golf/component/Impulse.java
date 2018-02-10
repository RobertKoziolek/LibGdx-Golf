package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class Impulse implements Component {
    public Vector2 impulse;

    public Impulse(final Vector2 impulse) {
        if (impulse == null) throw new IllegalArgumentException("Vector2 impulse cannot be null");
        this.impulse = impulse;
    }
}
