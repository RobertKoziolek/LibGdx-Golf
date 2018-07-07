package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyAssembler;

public class Bumper extends Entity {

    public Bumper(final Position position, final Dimension dimension, final float angle) {
        final Body body = BodyAssembler.circular(dimension)
                                       .withUserData(this)
                                       .withPosition(position)
                                       .withAngle(angle)
                                       .withCategoryBits(Bits.C.BALL_MANIPULANT)
                                       .withMaskBits(Bits.M.FREE_OBJECT_WILL_HIT)
                                       .withStatic(true)
                                       .withFixedRotation(true)
                                       .assemble();
        flags = EntityFlags.BUMPER.getId();

        add(new Selectable());
        add(position);
        add(dimension);
        add(Force.of(55f));
        add(Box2dBody.of(body));
        add(Renderable.of(TextureId.BUMPER));
    }

    public Bumper(final Ellipse ellipse, final float angle) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height), angle);
    }
}
