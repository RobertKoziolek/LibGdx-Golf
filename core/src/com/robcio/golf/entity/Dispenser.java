package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.*;
import com.robcio.golf.component.structure.*;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyAssembler;

public class Dispenser extends Entity {

    public Dispenser(final Position position, final Dimension dimension, final Force force) {
        final Body body = BodyAssembler.circular(dimension)
                                       .withUserData(this)
                                       .withPosition(position)
                                       .withAngle(0f)
                                       .withDensity(55.3f)
                                       .withLinearDamping(4f)
                                       .withAngularDamping(4f)
                                       .withCategoryBits(Bits.C.FREE_OBJECT)
                                       .withMaskBits(Bits.M.FREE_OBJECT_WILL_HIT)
                                       .withStatic(false)
                                       .withFixedRotation(false)
                                       .assemble();
        flags = EntityFlags.BALL.getId();

        add(new Selectable());
        add(Box2dBody.of(body));
        add(position);
        add(dimension);
        add(force);
        add(Renderable.of(TextureId.STAR));
        add(Timer.of(1f, ToRemove.self()));

        add(new Dispensing(new Recipe(position, dimension, BallType.WHITE)));
    }

    public Dispenser(final Rectangle rectangle, final Force force) {
        this(Position.of(rectangle.x, rectangle.y), Dimension.of(rectangle.width, rectangle.height), force);
    }
}
