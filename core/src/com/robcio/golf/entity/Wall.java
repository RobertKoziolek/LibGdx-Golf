package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.world.BodyAssembler;

public class Wall extends Entity {

    public Wall(final Shape shape) {
        final Body body = BodyAssembler.withShape(shape)
                                       .withUserData(this)
                                       .withCategoryBits(Bits.C.WALL)
                                       .withMaskBits(Bits.M.FREE_OBJECT_WILL_HIT)
                                       .withStatic(true)
                                       .assemble();

        add(Box2dBody.of(body));
    }
}