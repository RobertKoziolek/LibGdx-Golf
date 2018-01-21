package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.robcio.golf.component.*;
import com.robcio.golf.world.BodyFactory;

public class Hole extends Entity {

    public Hole(final Position position, final Dimension dimension) {
        final Body body = BodyFactory.createCircular(position, dimension, true, false, 4, 2);
        body.setUserData("hole");

        add(position);
        add(dimension);
        add(new Box2dBody(body));
        add(new Renderable("hole.png"));
    }
}
