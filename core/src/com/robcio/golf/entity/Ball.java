package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.flag.Tetherable;
import com.robcio.golf.component.graphics.Renderable;
import com.robcio.golf.component.graphics.Tinted;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.component.physics.InBowlable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyAssembler;

public class Ball extends Entity {

    public Ball(final Position position, final Dimension dimension, final float angle, final BallType ballType) {
        final Body body = BodyAssembler.circular(dimension)
                                       .withUserData(this)
                                       .withPosition(position)
                                       .withAngle(angle)
                                       .withRestitution(0.7f)
                                       .withDensity(0.8f)
                                       .withCategoryBits(Bits.C.BALL)
                                       .withMaskBits(Bits.M.BALL_WILL_HIT)
                                       .withStatic(false)
                                       .withFixedRotation(false)
                                       .withLinearDamping(0.6f)
                                       .withAngularDamping(0.6f)
                                       .assemble();
        //TODO moze na mape inny damping? generalnie powierzchnie dodac
        flags = EntityFlags.BALL.getId();

        add(new Selectable());
        add(new Tetherable());
        add(new InBowlable());
        add(position);
        add(dimension);
        add(Box2dBody.of(body));
        add(Renderable.of(TextureId.GOLFBALL, 20));

        if (ballType != null) {
            if (ballType == BallType.WHITE) {
                add(new Kickable());
                add(Particle.onFire());
            } else {
                add(Tinted.of(ballType.getColor()));
            }
        }

    }

    public Ball(final Ellipse ellipse, final float angle, final BallType ballType) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height), angle, ballType);
    }

    public static Ball of(final Recipe recipe) {
        return new Ball(recipe.getPosition()
                              .clone(), recipe.getDimension()
                                              .clone(), 0f, recipe.getBallType());
    }
}
