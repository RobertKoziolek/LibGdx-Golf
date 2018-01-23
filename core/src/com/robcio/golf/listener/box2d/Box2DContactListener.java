package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.robcio.golf.enumeration.EntityFlags;

import java.util.HashMap;
import java.util.Map;


public class Box2DContactListener implements ContactListener {

    final private Engine engine;

    final private HoleListener holeListener;
    final private BowlListener bowlListener;
    final private BumperListener bumperListener;

    public Box2DContactListener(final Engine engine){
        this.engine = engine;
        this.holeListener = new HoleListener(engine);
        this.bowlListener = new BowlListener(engine);
        this.bumperListener = new BumperListener(engine);
    }

    @Override
    public void beginContact(Contact contact) {
        final Map<Integer, Body> map = getIntegerBodyMap(contact);
        if (map == null) return;
        if (map.containsKey(EntityFlags.NONE.getId())) return;

        if (ContainsFlags(map, EntityFlags.BALL, EntityFlags.HOLE)) {
            holeListener.beginContact(map);
        } else if (ContainsFlags(map, EntityFlags.BALL, EntityFlags.BOWL)) {
            bowlListener.beginContact(map);
        }
    }

    @Override
    public void endContact(Contact contact) {
        final Map<Integer, Body> map = getIntegerBodyMap(contact);
        if (map == null) return;
        if (map.containsKey(EntityFlags.NONE.getId())) return;
        if (ContainsFlags(map, EntityFlags.BALL, EntityFlags.BUMPER)) {
            bumperListener.beginContact(map);
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
        return map;
    }

    private boolean ContainsFlags(final Map<Integer, Body> map, final EntityFlags flagA, final EntityFlags flagB) {
        return map.containsKey(flagA.getId()) && map.containsKey(flagB.getId());
    }
}
