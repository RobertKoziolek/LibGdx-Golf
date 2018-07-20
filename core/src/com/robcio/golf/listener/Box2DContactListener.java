package com.robcio.golf.listener;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.registrar.BodyListenerRegistrar;

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

        for (BodyListener listener: registrar.getListeners()) {
            if (containsFlags(map, listener.getEntityFlagsA(), listener.getEntityFlagsB())) {
                listener.beginContact(map);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        final Map<Integer, Body> map = getIntegerBodyMap(contact);
        if (map == null) return;

        for (BodyListener listener: registrar.getListeners()) {
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
        final Body bodyA = contact.getFixtureA()
                                  .getBody();
        final Body bodyB = contact.getFixtureB()
                                  .getBody();
        final Entity entityA;
        final Entity entityB;
        try {
            entityA = (Entity) bodyA.getUserData();
            entityB = (Entity) bodyB.getUserData();
        } catch (final ClassCastException e) {
            return null;
        }
        if (entityA == null || entityB == null) return null;

        final Map<Integer, Body> map = new HashMap<>(2);
        map.put(entityA.flags, bodyA);
        map.put(entityB.flags, bodyB);
        if (map.containsKey(EntityFlags.NONE.getId())) return null;
        return map;
    }

    private boolean containsFlags(final Map<Integer, Body> map,
                                  final EntityFlags[] flagsA,
                                  final EntityFlags[] flagsB) {
        final Bits bits = new Bits();
        for (final Integer flag: map.keySet()) {
            bits.set(flag);
        }
        return bits.intersects(getBits(flagsA)) && bits.intersects(getBits(flagsB));
    }

    private Bits getBits(final EntityFlags[] flags) {
        final Bits bits = new Bits();
        for (EntityFlags flag: flags) {
            bits.set(flag.getId());
        }
        return bits;
    }
}
