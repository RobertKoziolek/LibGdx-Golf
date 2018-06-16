package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class PositionSynchronizationSystem extends IteratingSystem {


    public PositionSynchronizationSystem(final int priority) {
        super(Family.all(Box2dBody.class, Position.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final Body body = Mapper.box2dBody.get(entity).body;
        final Position position = Mapper.position.get(entity);

        final Vector2 scaledPosition = body.getPosition().cpy().scl(Maths.PPM);
        position.set(scaledPosition);
    }
}
