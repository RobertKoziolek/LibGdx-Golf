package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.robcio.golf.component.*;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyFactory;

public class Hole extends Entity {

    public Hole(final Position position, final Dimension dimension) {
        final Body body = BodyFactory.createCircular(position, dimension, true, true, 4, 2);
        body.setUserData(this);
        flags = EntityFlags.HOLE.getId();

        add(position);
        add(dimension);
        add(new Box2dBody(body));
        add(Renderable.of(Textures.HOLE, 30));
    }
}
