package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.world.BodyFactory;

public class Obstacle extends Entity {

    public Obstacle(final Position position, final Dimension dimension) {
        final Body body = BodyFactory
                .createBox(position, dimension, true, true, Bits.C.WALL, Bits.M.FREE_OBJECT_WILL_HIT);
        body.setUserData(this);

        add(position);
        add(dimension);
        add(new Box2dBody(body));
    }
}