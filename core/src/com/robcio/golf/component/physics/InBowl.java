package com.robcio.golf.component.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.structure.Dimension;
import lombok.NonNull;

public class InBowl implements Component {
    public Vector2 bowlCenter;
    public Dimension bowlDimension;
    public final float deepness;

    public InBowl(@NonNull final Vector2 bowlCenter, @NonNull final Dimension bowlDimension){
        this.bowlCenter = bowlCenter;
        this.bowlDimension = bowlDimension;
        this.deepness =  bowlDimension.width / 9f;
    }
}
