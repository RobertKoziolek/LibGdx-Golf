package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.flag.Tetherable;
import com.robcio.golf.component.graphics.Renderable;
import com.robcio.golf.component.physics.InBowlable;
import com.robcio.golf.component.physics.OnSlopable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyAssembler;

public class Box extends Entity {

    public Box(final Position position, final Dimension dimension, final float angle) {
        final Body body = BodyAssembler.box(dimension)
                                       .withUserData(this)
                                       .withPosition(position)
                                       .withAngle(angle)
                                       .withDensity(2f)
                                       .withLinearDamping(4f)
                                       .withAngularDamping(4f)
                                       .withCategoryBits(Bits.C.FREE_OBJECT)
                                       .withMaskBits(Bits.M.FREE_OBJECT_WILL_HIT)
                                       .withStatic(false)
                                       .withFixedRotation(false)
                                       .assemble();
        flags = EntityFlags.BOX.getId();

        add(new Selectable());
        add(new Tetherable());
        add(new InBowlable());
        add(new OnSlopable());
        add(Box2dBody.of(body));
        add(position);
        add(dimension);
        add(Renderable.of(TextureId.BOX));
        add(new Kickable());
    }

    public Box(final Rectangle rectangle, final float angle) {
        this(Position.of(rectangle.x, rectangle.y), Dimension.of(rectangle.width, rectangle.height), angle);
    }
}
