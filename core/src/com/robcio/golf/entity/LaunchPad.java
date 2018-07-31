package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.graphics.Renderable;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.Dispensing;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyAssembler;

public class LaunchPad extends Entity {

    public LaunchPad(final Position position, final Dimension dimension, final float angle) {
        final Body body = BodyAssembler.circular(dimension)
                                       .withUserData(this)
                                       .withPosition(position)
                                       .withAngle(angle)
                                       .withCategoryBits(Bits.C.BALL_MANIPULANT)
                                       .withMaskBits(Bits.M.BALL)
                                       .withStatic(true)
                                       .withSensor(true)
                                       .withFixedRotation(true)
                                       .assemble();
        flags = EntityFlags.LAUNCHPAD.getId();

        add(new Dispensing(Recipe.of(Ball.class)
                                 .withPosition(position)
                                 .withDimension(Dimension.of(30f))
                                 .withBallType(BallType.WHITE)
                                 .withEntityFlags(EntityFlags.LAUNCHING_BALL)
                                 .withParticle(Particle.onWater())
                                 .assemble(), true));
        add(new Selectable());
        add(position);
        add(dimension);
        add(Box2dBody.of(body));
        add(Renderable.of(TextureId.LAUNCHPAD, 5));
    }

    public LaunchPad(final Ellipse ellipse, final float angle) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height), angle);
    }
}
