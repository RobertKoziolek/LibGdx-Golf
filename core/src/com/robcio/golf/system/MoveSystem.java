package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;

public class MoveSystem extends IteratingSystem {

    private Position position;

    public MoveSystem() {
        super(Family.all(Selected.class, Box2dBody.class).get());
        setProcessing(false);
    }

    @Override
    public void update(final float deltaTime){
        position = Selected.position;
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Body body = Mapper.box2dBody.get(entity).body;
        body.setTransform(position.x, position.y, body.getAngle());
        body.setLinearVelocity(Vector2.Zero);
        body.setAngularVelocity(0f);
    }
}
