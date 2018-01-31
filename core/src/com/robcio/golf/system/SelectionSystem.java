package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;

public class SelectionSystem extends IteratingSystem {

    public SelectionSystem() {
        super(Family.all(Selected.class, Box2dBody.class).get());
        setProcessing(true);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final Body body = Mapper.box2dBody.get(entity).body;
        final Position position = Selected.position;

        body.setTransform(position.x, position.y, body.getAngle());





//        if (!Gdx.input.isButtonPressed(0))return;
//
//        Vector2 distance = new Vector2(0f, 0f);
//        distance.add(body.getPosition());
//        final Position position = Selected.position;
//        distance.sub(new Vector2(position.x, position.y));
//        float finalDistance = distance.len();
//        distance = new Vector2(-distance.x, -distance.y);
//
//        float vecSum = Math.abs(distance.x) + Math.abs(distance.y);
//        final int force = 1;
//
//        if (vecSum < 0.6f) {
//            body.setLinearVelocity(body.getLinearVelocity().scl(0.9f));
//            body.setAngularVelocity(body.getAngularVelocity() * 0.9f);
//        } else {
//            distance = new Vector2(distance.x * (1/vecSum) * force * finalDistance,
//                                   distance.y * (1/vecSum) * force * finalDistance);
//            body.applyForceToCenter(distance, true);
//        }
    }
}
