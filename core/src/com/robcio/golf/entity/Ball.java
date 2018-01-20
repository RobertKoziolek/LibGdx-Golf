package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.world.BodyFactory;

public class Ball extends Entity {

    public Ball(final Position position, final Dimension dimension) {
        add(position);
        add(dimension);
        add(new Velocity());
        final Body body = BodyFactory.createCircular(position, dimension, false, false, 2, 3);
        body.getFixtureList().get(0).setRestitution(0.5f);
        add(new Box2dBody(body));
        add(new Impulse());
    }
}
