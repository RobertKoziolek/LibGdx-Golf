package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.structure.*;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Log;
import com.robcio.golf.world.BodyFactory;

public class Slope extends Entity {

    public Slope(final Position position, final Dimension dimension, final float angle, final Direction direction,
                 final Force force) {
        final Body body = BodyFactory
                .createBox(position, dimension, true, true, angle, Bits.C.BALL_MANIPULANT, Bits.C.BALL);

        body.setUserData(this);
        body.getFixtureList().get(0).setSensor(true);
        flags = EntityFlags.SLOPE.getId();

//        add(new Selectable());
        add(position);
        add(dimension);
        add(direction);
        add(force);
        add(Box2dBody.of(body));

        final Renderable renderable = Renderable.of(TextureId.SLOPE, 5);
        add(renderable);
    }

    public Slope(final Rectangle rectangle, final float angle, final Direction direction, final Force force) {
        this(Position.of(rectangle.x, rectangle.y), Dimension.of(rectangle.width, rectangle.height), angle, direction,
             force);
    }
}