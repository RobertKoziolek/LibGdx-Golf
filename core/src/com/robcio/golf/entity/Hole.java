package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyFactory;

public class Hole extends Entity {

    public Hole(final Position position, final Dimension dimension) {
        final Body body = BodyFactory.createCircular(position, dimension, true, true, Bits.C.BALL_MANIPULANT, Bits.C.BALL);
        body.setUserData(this);
        flags = EntityFlags.HOLE.getId();

        add(position);
        add(dimension);
        add(new Box2dBody(body));
        //TODO cos z tym trzeba zrobic zeby to jednak bylo autonomicznie holem bez wspolpracy z bowlem
//        add(Renderable.of(Textures.HOLE, 15));
    }
}
