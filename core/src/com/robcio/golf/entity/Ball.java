package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.robcio.golf.component.*;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyFactory;

//TODO dwa systemy ruchu pilki/pilek - naciaganie jak golf/proca, oraz do celu - klikam dokad ma poleciec
public class Ball extends Entity {

    public Ball(final Position position, final Dimension dimension) {
        final Body body = BodyFactory.createCircular(position, dimension, false, false, 2, 7);
        body.setUserData(this);
        flags = EntityFlags.BALL.getId();

        final Fixture fixture = body.getFixtureList().get(0);
        fixture.setRestitution(0.5f);
        fixture.setDensity(0.4f);

        add(position);
        add(new Attracted());
        add(dimension);
        add(new Box2dBody(body));
        add(new Impulse());
        add(Renderable.of(Textures.GOLFBALL, 20));
    }
}
