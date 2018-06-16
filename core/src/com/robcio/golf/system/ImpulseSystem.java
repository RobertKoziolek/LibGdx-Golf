package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Impulse;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;

import java.util.Random;

public class ImpulseSystem extends IteratingSystem {

    public ImpulseSystem(final int priority) {
        super(Family.all(Impulse.class, Box2dBody.class).get(), priority);
        setProcessing(false);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        Log.i("jeblem" + new Random().nextInt());
        final Vector2 impulse = Mapper.impulse.get(entity).impulse;
        final Box2dBody box2dBody = Mapper.box2dBody.get(entity);
        box2dBody.body.applyForceToCenter(impulse, true);
        entity.remove(Impulse.class);
    }
}
