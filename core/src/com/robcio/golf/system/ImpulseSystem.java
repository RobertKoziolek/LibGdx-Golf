package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Impulse;
import com.robcio.golf.utils.Mapper;

public class ImpulseSystem extends IteratingSystem {

    public ImpulseSystem(final int priority) {
        super(Family.all(Impulse.class, Box2dBody.class).get(), priority);
        setProcessing(true);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Vector2 impulse = Mapper.impulse.get(entity).impulse;
        final Box2dBody box2dBody = Mapper.box2dBody.get(entity);

        box2dBody.body.applyForceToCenter(impulse, true);
        entity.add(ToRemove.of(Impulse.class));
    }
}
