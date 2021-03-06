package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.world.BodyAssembler;

public class Obstacle extends Entity {

    public Obstacle(final Position position, final Dimension dimension, final float angle) {
        final Body body = BodyAssembler.box(dimension)
                                       .withUserData(this)
                                       .withPosition(position)
                                       .withAngle(angle)
                                       .withCategoryBits(Bits.C.WALL)
                                       .withMaskBits(Bits.M.FREE_OBJECT_WILL_HIT)
                                       .withStatic(true)
                                       .withFixedRotation(true)
                                       .assemble();

        add(new Selectable());
        add(position);
        add(dimension);
        add(Box2dBody.of(body));
    }

    public Obstacle(final Rectangle rectangle, final float angle) {
        this(Position.of(rectangle.x, rectangle.y), Dimension.of(rectangle.width, rectangle.height), angle);
    }
}