package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
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
        body.setLinearVelocity(Vector2.Zero);
        body.setAngularVelocity(0f);
    }
}
