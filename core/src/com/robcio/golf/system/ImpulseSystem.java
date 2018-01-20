package com.robcio.golf.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.*;
import com.robcio.golf.utils.Maths;

import java.util.Random;

public class ImpulseSystem extends IntervalIteratingSystem {
    private ImmutableArray<Entity> entities;

    final private ComponentMapper<Impulse> im = ComponentMapper.getFor(Impulse.class);
    final private ComponentMapper<Box2dBody> b2dm = ComponentMapper.getFor(Box2dBody.class);

    public ImpulseSystem(final float interval) {
        super(Family.all(Impulse.class, Box2dBody.class).get(), interval);
    }

    @Override
    protected void processEntity(Entity entity) {
        final Impulse impulse = im.get(entity);
        impulse.x = Maths.nextFloat() * 200 - 100;
        impulse.y = Maths.nextFloat() * 200 - 100;

        final Box2dBody box2dBody = b2dm.get(entity);
        box2dBody.body.applyForceToCenter(new Vector2(impulse.x, impulse.y), true);
    }
}
