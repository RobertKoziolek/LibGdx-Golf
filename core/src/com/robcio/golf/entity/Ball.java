package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.robcio.golf.component.*;
import com.robcio.golf.world.BodyFactory;

public class Ball extends Entity {

    public Ball(final Position position, final Dimension dimension) {
        add(position);
        add(dimension);
        final Body body = BodyFactory.createCircular(position, dimension, false, false, 2, 3);
        final Fixture fixture = body.getFixtureList().get(0);
        fixture.setRestitution(0.5f);
        fixture.setDensity(0.4f);
        add(new Box2dBody(body));
        add(new Impulse());
        add(new Renderable("golfball.png"));
    }
}
