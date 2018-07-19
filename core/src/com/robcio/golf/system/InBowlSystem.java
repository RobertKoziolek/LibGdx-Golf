package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.physics.InBowl;
import com.robcio.golf.component.physics.InBowlable;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class InBowlSystem extends IteratingSystem {

    public InBowlSystem(final int priority) {
        super(Family.one(InBowlable.class, InBowl.class)
                    .get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (Mapper.inBowl.get(entity) != null) {
            processInBowl(entity, Mapper.inBowl.get(entity));
        } else {
            final InBowlable inBowlable = Mapper.inBowlable.get(entity);
            if (inBowlable != null) {
                for (final InBowl inBowl: inBowlable.set) {
                    processInBowl(entity, inBowl);
                }
            }
        }
    }

    private void processInBowl(final Entity entity, final InBowl inBowl) {
        final Body ball = Mapper.box2dBody.get(entity).body;
        final Dimension bowlDimension = Dimension.radiusToBox2D(inBowl.bowlDimension);
        final Dimension ballDimension = Dimension.radiusToBox2D(Mapper.dimension.get(entity));
        final float bowlRadius = bowlDimension.width;
        final float deepness = inBowl.deepness;
        final float ballRadius = ballDimension.width;

        final Vector2 inwardVector = getInwardVector(ball.getPosition(), inBowl.bowlCenter);
        final float distanceLength = inwardVector.len();
        final float vecSum = Maths.getVectorSum(inwardVector);

        //TODO to ze po srodku jest plasko zle wyglada kiedy orbituje, zamiast ladne kolko jest kanciato
        if (distanceLength > bowlRadius / 5f && distanceLength < getRadiusToRim(bowlRadius, ballRadius)) {
            ball.applyForceToCenter(
                    inwardVector.scl(1 / vecSum * deepness * distanceLength * 1 / (bowlRadius * bowlRadius)), true);
        }
    }

    private float getRadiusToRim(float bowlRadius, float ballRadius) {
        return bowlRadius - ballRadius / 2 - bowlRadius / 33;
    }

    private Vector2 getInwardVector(final Vector2 vector1, final Vector2 vector2) {
        Vector2 distance = Maths.getDistance(vector1, vector2);
        return Maths.inverse(distance);
    }

}
