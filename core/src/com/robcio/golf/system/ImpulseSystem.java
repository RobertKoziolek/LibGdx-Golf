package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Impulse;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class ImpulseSystem extends IntervalIteratingSystem {

    private boolean processing = false;

    public ImpulseSystem(final float interval) {
        super(Family.all(Impulse.class, Box2dBody.class).get(), interval);
        setProcessing(processing);
    }

    @Override
    protected void processEntity(Entity entity) {
        final float x = Maths.nextFloat() * 200 - 100;
        final float y = Maths.nextFloat() * 200 - 100;

        final Box2dBody box2dBody = Mapper.box2dBody.get(entity);
        box2dBody.body.applyForceToCenter(new Vector2(x, y), true);
    }

    public boolean change() {
        processing = !processing;
        setProcessing(processing);
        return processing;
    }
}
