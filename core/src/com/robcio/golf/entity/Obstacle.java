package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.world.BodyFactory;

public class Obstacle extends Entity {

    public Obstacle(final Position position, final Dimension dimension) {
        final Body body = BodyFactory
                .createBox(position, dimension, true, true, Bits.C.WALL, Bits.M.FREE_OBJECT_WILL_HIT);
        body.setUserData(this);

        add(new Selectable());
        add(position);
        add(dimension);
        add(Box2dBody.of(body));
    }

    public Obstacle(final Rectangle rectangle) {
        this(Position.of(rectangle.x, rectangle.y), Dimension.of(rectangle.width, rectangle.height));
    }
}