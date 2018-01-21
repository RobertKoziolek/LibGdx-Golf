package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.robcio.golf.utils.Log;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class HoleBallListener implements ContactListener {

    final private Engine engine;

    @Override
    public void beginContact(Contact contact) {
        final Entity entityA = (Entity) contact.getFixtureA().getBody().getUserData();
        final Entity entityB = (Entity) contact.getFixtureB().getBody().getUserData();
        if (entityA == null || entityB == null) return;

        final Map<Integer, Entity> map = new HashMap<>(2);
        map.put(entityA.flags, entityA);
        map.put(entityB.flags, entityB);
        if (map.containsKey(0)) return;

        if (map.containsKey(1) && map.containsKey(2)) {
            final Entity ballEntity = map.get(1);
            engine.removeEntity(ballEntity);
            Log.i("Collision", "Ball went into the hole");
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
