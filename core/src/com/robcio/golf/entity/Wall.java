package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.world.BodyFactory;

public class Wall extends Entity {

    public Wall(final Shape shape) {
        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Bits.C.WALL;
        fixtureDef.filter.maskBits = Bits.M.FREE_OBJECT_WILL_HIT;

        final BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;

        final Body body;
        body = BodyFactory.createBody(def);
        body.createFixture(fixtureDef);
        body.setUserData(this);

        add(new Box2dBody(body));
    }
}