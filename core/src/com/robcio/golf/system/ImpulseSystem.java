package com.robcio.golf.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.Log;
import com.robcio.golf.component.*;

import java.util.Random;

public class ImpulseSystem extends IntervalIteratingSystem {
    private ImmutableArray<Entity> entities;

    final private Random random = new Random();

    final private ComponentMapper<Impulse> im = ComponentMapper.getFor(Impulse.class);
    final private ComponentMapper<Box2dBody> b2dm = ComponentMapper.getFor(Box2dBody.class);

    public ImpulseSystem(final float interval) {
        super(Family.all(Impulse.class, Box2dBody.class).get(), interval);
    }

    @Override
    protected void processEntity(Entity entity) {
        final Impulse impulse = im.get(entity);
        impulse.x = random.nextFloat() * 200 - 100;
        impulse.y = random.nextFloat() * 200 - 100;

        final Box2dBody box2dBody = b2dm.get(entity);
        box2dBody.body.applyForceToCenter(new Vector2(impulse.x, impulse.y), true);
    }
}
