package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.light.Light;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.Timer;
import com.robcio.golf.component.util.ToRemove;

//TODO do zmianny nazwa
public class LightEntity extends Entity {

    public LightEntity(final Position position) {
        add(position);
        add(new Light());
        add(Timer.of(3f, ToRemove.self()));
    }
}
