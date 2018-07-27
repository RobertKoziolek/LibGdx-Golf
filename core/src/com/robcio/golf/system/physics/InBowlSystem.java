package com.robcio.golf.system.physics;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.physics.InBowl;
import com.robcio.golf.component.physics.InBowlable;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.system.GroupedIteratingSystem;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;
import lombok.Getter;

@Getter
public class InBowlSystem extends GroupedIteratingSystem {
    private final ComponentMapper groupMapper = Mapper.inBowlable;

    public InBowlSystem(final int priority) {
        super(Family.all(InBowlable.class)
                    .get(), priority);
    }

    @Override
    protected void processEntity(final Entity entity, final InGroup inGroup) {
        processInBowl(entity, (InBowl) inGroup);
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
        final Vector2 distance = Maths.getDistance(vector1, vector2);
        return Maths.inverse(distance);
    }

}
