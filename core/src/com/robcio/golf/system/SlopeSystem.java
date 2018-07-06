package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.OnSlope;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.utils.Mapper;

public class SlopeSystem extends IteratingSystem {

    public SlopeSystem(final int priority) {
        super(Family.all(OnSlope.class, Box2dBody.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final Body ball = Mapper.box2dBody.get(entity).body;
        final OnSlope onSlope = Mapper.onSlope.get(entity);
        final Vector2 direction = onSlope.direction.value;
        //TODO te dzielenie to bzdura
        final float force = onSlope.force.value/5;

        ball.applyForceToCenter(direction.scl(force), true);
    }
}
