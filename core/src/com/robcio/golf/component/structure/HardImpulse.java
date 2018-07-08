package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import lombok.NonNull;

public class HardImpulse implements Component {
    public Vector2 impulse;

    public HardImpulse(@NonNull final Vector2 impulse) {
        this.impulse = impulse;
    }
}
