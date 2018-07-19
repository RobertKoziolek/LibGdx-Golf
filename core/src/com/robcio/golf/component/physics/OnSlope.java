package com.robcio.golf.component.physics;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.structure.Direction;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.util.InGroup;

public class OnSlope extends InGroup {
    public Direction direction;
    public Force force;

    public OnSlope(final Direction direction, final Force force, final Entity groupedBy) {
        super(groupedBy);
        this.direction = direction;
        this.force = force;
    }
}
