package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.world.BodyAssembler;

//TODO dodac jakis vortex czy cos, zabawa z fizyka
public class Hole extends Entity {

    public Hole(final Position position, final Dimension dimension, final float angle) {
        final Body body = BodyAssembler.circular(Dimension.of(1f))
                                       .withUserData(this)
                                       .withPosition(position)
                                       .withAngle(angle)
                                       .withCategoryBits(Bits.C.BALL_MANIPULANT)
                                       .withMaskBits(Bits.M.BALL)
                                       .withStatic(true)
                                       .withFixedRotation(true)
                                       .assemble();
        flags = EntityFlags.HOLE.getId();

        add(new Selectable());
        add(position);
        add(dimension);
        add(Box2dBody.of(body));
        //TODO zmienic nazwy sensownie
        add(new com.robcio.golf.component.flag.Hole());
    }

    public Hole(final Ellipse ellipse, final float angle) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height), angle);
    }
}
