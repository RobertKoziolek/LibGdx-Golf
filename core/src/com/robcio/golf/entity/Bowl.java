package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyAssembler;

public class Bowl extends Entity {

    public Bowl(final Position position, final Dimension dimension, final float angle, final TextureId textureId) {
        final Body body = BodyAssembler.circular(dimension)
                                       .withSensor(true)
                                       .withUserData(this)
                                       .withPosition(position)
                                       .withAngle(angle)
                                       .withCategoryBits(Bits.C.BALL_MANIPULANT)
                                       .withMaskBits(Bits.C.BALL)//TODO dlaczego mask bits jest z CategoryBitow?
                                       .withStatic(true)
                                       .withFixedRotation(true)
                                       .assemble();
        flags = EntityFlags.BOWL.getId();

        add(new Selectable());
        add(position);
        add(dimension);
        add(Box2dBody.of(body));
        add(Renderable.of(textureId, 5));
    }

    public Bowl(final Ellipse ellipse, final float angle, final TextureId textureId) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height), angle, textureId);
    }
}
