package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class InBowlSystem extends IteratingSystem {

    public InBowlSystem() {
        super(Family.all(InBowl.class, Box2dBody.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final Body ball = Mapper.box2dBody.get(entity).body;
        final InBowl inBowl = Mapper.inBowl.get(entity);
        final Dimension bowlDimension = Dimension.radiusToBox2D(inBowl.bowlDimension);
        final Dimension ballDimension = Dimension.radiusToBox2D(Mapper.dimension.get(entity));
        final float bowlRadius = bowlDimension.width;
        final float ballRadius = ballDimension.width;

        final Vector2 distance = getDistance(ball.getPosition(), inBowl.bowlCenter);

        final float finalDistance = distance.len();

        //TODO zbalansowac sile aby wielkosc bowla odzwierciedlala jak mocno cos wpada
        float vecSum = Maths.getVectorSum(distance);
        vecSum -= bowlRadius / 2;

        final int force = 1;

        if (vecSum < 0) {
            dampVelocities(ball);
        } else if (finalDistance < bowlRadius - ballRadius - bowlRadius / 24) {
            ball.applyForceToCenter(distance.scl(vecSum * force * finalDistance), true);
        }
    }

    private void dampVelocities(final Body body) {
        body.setLinearVelocity(body.getLinearVelocity().scl(0.975f));
        body.setAngularVelocity(body.getAngularVelocity() * 0.975f);
    }

    private Vector2 getDistance(final Vector2 vector1, final Vector2 vector2) {
        Vector2 distance = Maths.getDistance(vector1, vector2);
        return Maths.inverse(distance);
    }

}
