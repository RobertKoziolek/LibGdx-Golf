package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import lombok.NonNull;

public class InBowl implements Component {
    public Vector2 bowlCenter;
    public Dimension bowlDimension;

    public InBowl(@NonNull final Vector2 bowlCenter, @NonNull final Dimension bowlDimension){
        this.bowlCenter = bowlCenter;
        this.bowlDimension = bowlDimension;
    }
}
