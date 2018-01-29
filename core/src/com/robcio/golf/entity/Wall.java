package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyFactory;

public class Wall extends Entity {

    public Wall(final Position position, final Dimension dimension) {
        final Body body = BodyFactory
                .createBox(position, dimension, true, true, Bits.C.WALL, Bits.M.FREE_OBJECT_WILL_HIT);
        body.setUserData(this);
        add(new Box2dBody(body));
        add(position);
        add(dimension);
    }

    public Wall(final Shape shape) {

        Body body;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        body = BodyFactory.getWorld().createBody(def);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Bits.C.WALL;
        fixtureDef.filter.maskBits = Bits.M.FREE_OBJECT_WILL_HIT;
        body.createFixture(fixtureDef);
        body.setUserData(this);
    }
}