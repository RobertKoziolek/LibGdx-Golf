package com.robcio.golf.component.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.util.InGroup;
import lombok.NonNull;

public class InBowl extends InGroup {
    public Vector2 bowlCenter;
    public Dimension bowlDimension;
    public final float deepness;

    public InBowl(@NonNull final Vector2 bowlCenter, @NonNull final Dimension bowlDimension, final Entity groupedBy) {
        super(groupedBy);
        this.bowlCenter = bowlCenter;
        this.bowlDimension = bowlDimension;
        this.deepness = bowlDimension.width / 9f;
    }
}
