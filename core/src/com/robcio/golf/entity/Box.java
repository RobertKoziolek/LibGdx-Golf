package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyFactory;

public class Box extends Entity {

    public Box(final Position position, final Dimension dimension){
        final Body body = BodyFactory.createBox(position, dimension, false, false, 8, 11);
        body.setUserData(this);
        add(new Box2dBody(body));
        add(position);
        add(dimension);
        add(Renderable.of(Textures.BOX));
        add(new Impulse());
    }
}
