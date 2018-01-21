package com.robcio.golf.listener.box2d;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.robcio.golf.utils.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HoleBallListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        final Body bodyA = contact.getFixtureA().getBody();
        final Body bodyB = contact.getFixtureB().getBody();
        final Map<String, Body> map = new HashMap<>();
        map.put((String) bodyA.getUserData(), bodyA);
        map.put((String) bodyB.getUserData(), bodyB);
        if (map.containsKey(null)) return;
        if (map.containsKey("hole") && map.containsKey("ball")) {
            final Body ballBody = map.get("ball");
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
