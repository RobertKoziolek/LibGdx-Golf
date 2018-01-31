package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Log;
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
        final Vector2 bowlCenter = inBowl.bowlCenter;
        final float bowlRadius = inBowl.bowlDimension.width/Maths.PPM;
        final float ballRadius = Mapper.dimension.get(entity).width/2/Maths.PPM;

        Vector2 distance = new Vector2(0f, 0f);
        distance.add(ball.getPosition());
        distance.sub(bowlCenter);
        float finalDistance = distance.len();
        distance = new Vector2(-distance.x, -distance.y);

        //TODO zbalansowac sile aby wielkosc bowla odzwierciedlala jak mocno cos wpada
        float vecSum = Math.abs(distance.x) + Math.abs(distance.y);
        vecSum = vecSum - bowlRadius/2;
        final int force = 1;

        if (vecSum < 0) {
            ball.setLinearVelocity(ball.getLinearVelocity().scl(0.97f));
            ball.setAngularVelocity(ball.getAngularVelocity() * 0.97f);
        } else if (finalDistance < bowlRadius - ballRadius - bowlRadius/12){
            distance = new Vector2(distance.x * (vecSum) * force * finalDistance,
                                   distance.y * (vecSum) * force * finalDistance);
            ball.applyForceToCenter(distance, true);
        }
    }
}
