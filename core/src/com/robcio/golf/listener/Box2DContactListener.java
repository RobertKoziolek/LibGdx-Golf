package com.robcio.golf.listener;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.robcio.golf.enumeration.EntityFlags;

import java.util.HashMap;
import java.util.Map;


public class Box2DContactListener implements ContactListener {

    final private BodyListenerRegistrar registrar;

    public Box2DContactListener(final Engine engine) {
        this.registrar = new BodyListenerRegistrar(engine);
    }

    @Override
    public void beginContact(Contact contact) {
        final Map<Integer, Body> map = getIntegerBodyMap(contact);
        if (map == null) return;

        for (BodyListener listener : registrar.getListeners()) {
            if (containsFlags(map, listener.getEntityFlagsA(), listener.getEntityFlagsB())) {
                listener.beginContact(map);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        final Map<Integer, Body> map = getIntegerBodyMap(contact);
        if (map == null) return;

        for (BodyListener listener : registrar.getListeners()) {
            if (containsFlags(map, listener.getEntityFlagsA(), listener.getEntityFlagsB())) {
                listener.endContact(map);
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //nothing to do here
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        //nothing to do here
    }

    private Map<Integer, Body> getIntegerBodyMap(final Contact contact) {
        final Body bodyA = contact.getFixtureA().getBody();
        final Body bodyB = contact.getFixtureB().getBody();
        final Entity entityA = (Entity) bodyA.getUserData();
        final Entity entityB = (Entity) bodyB.getUserData();
        if (entityA == null || entityB == null) return null;

        final Map<Integer, Body> map = new HashMap<>(2);
        map.put(entityA.flags, bodyA);
        map.put(entityB.flags, bodyB);
        if (map.containsKey(EntityFlags.NONE.getId())) return null;
        return map;
    }

    private boolean containsFlags(final Map<Integer, Body> map, final EntityFlags flagA, final EntityFlags flagB) {
        return map.containsKey(flagA.getId()) && map.containsKey(flagB.getId());
    }
}
