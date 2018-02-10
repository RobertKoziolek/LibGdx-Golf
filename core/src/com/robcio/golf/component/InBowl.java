package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class InBowl implements Component {
    public Vector2 bowlCenter;
    public Dimension bowlDimension;

    public InBowl (final Vector2 bowlCenter, final Dimension bowlDimension){
        if (bowlCenter == null) throw new IllegalArgumentException("Vector2 bowlCenter cannot be null");
        if (bowlDimension == null) throw new IllegalArgumentException("Dimension bowlDimension cannot be null");
        this.bowlCenter = bowlCenter;
        this.bowlDimension = bowlDimension;
    }
}
