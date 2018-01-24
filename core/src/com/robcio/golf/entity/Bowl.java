package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyFactory;

public class Bowl extends Entity {

    public Bowl(final Position position, final Dimension dimension, final String texture) {
        final Body body = BodyFactory.createCircular(position, dimension, true, true, 4, 2);

        body.setUserData(this);
        body.getFixtureList().get(0).setSensor(true);
        flags = EntityFlags.BOWL.getId();

        add(position);
        add(dimension);
        add(new Box2dBody(body));
        add(Renderable.of(texture, 5));
    }
}
