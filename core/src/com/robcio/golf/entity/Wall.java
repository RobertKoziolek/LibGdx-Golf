package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.world.BodyFactory;

public class Wall extends Entity {

    public Wall(final Position position, final Dimension dimension){
        add(position);
        add(dimension);
        BodyFactory.createBox(position, dimension, true, true, 1, 2);
    }
}
