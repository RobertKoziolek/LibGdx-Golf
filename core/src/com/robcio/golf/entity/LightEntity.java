package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.light.Light;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Position;

//TODO do zmianny nazwa
public class LightEntity extends Entity {

    public LightEntity(final Position position, final Force force) {
        add(position);
        add(force);
        add(new Light());
    }
}
