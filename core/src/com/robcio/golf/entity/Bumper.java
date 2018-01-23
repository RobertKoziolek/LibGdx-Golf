package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyFactory;

public class Bumper extends Entity {

    public Bumper(final Position position, final Dimension dimension, final Force force) {
        final Body body = BodyFactory.createCircular(position, dimension, true, true, 4, 2);

        body.setUserData(this);
        flags = EntityFlags.BUMPER.getId();

        add(position);
        add(dimension);
        add(force);
        add(new Box2dBody(body));
        add(new Renderable(Textures.BUMPER));
    }
}
