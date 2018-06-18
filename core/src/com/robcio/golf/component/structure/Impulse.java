package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.utils.Log;
import lombok.NonNull;

public class Impulse implements Component {
    public Vector2 impulse;

    public Impulse(@NonNull final Vector2 impulse) {
        this.impulse = impulse;
        Log.i("dodaje sie");
    }
}
