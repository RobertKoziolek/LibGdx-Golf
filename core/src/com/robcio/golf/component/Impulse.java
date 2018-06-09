package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import lombok.NonNull;

public class Impulse implements Component {
    public Vector2 impulse;

    public Impulse(@NonNull final Vector2 impulse) {
        this.impulse = impulse;
    }
}
