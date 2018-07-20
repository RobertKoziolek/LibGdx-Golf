package com.robcio.golf.listener;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.physics.box2d.*;
import com.robcio.golf.registrar.BodyListenerRegistrar;

public class Box2DContactListener implements ContactListener {

    final private BodyListenerRegistrar registrar;

    public Box2DContactListener(final Engine engine) {
        this.registrar = new BodyListenerRegistrar(engine);
    }

    @Override
    public void beginContact(Contact contact) {
        final EntityHolder entityHolder = getEntities(contact);
        if (entityHolder == null) return;

        for (BodyListener listener: registrar.getListeners()) {
            if (entityHolder.containsFlags(listener.getEntityFlagsA(), listener.getEntityFlagsB())) {
                listener.beginContact(entityHolder);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        final EntityHolder entityHolder = getEntities(contact);
        if (entityHolder == null) return;

        for (BodyListener listener: registrar.getListeners()) {
            if (entityHolder.containsFlags(listener.getEntityFlagsA(), listener.getEntityFlagsB())) {
                listener.endContact(entityHolder);
            }
        }
    }

    private EntityHolder getEntities(final Contact contact) {
        final Body bodyA = contact.getFixtureA()
                                  .getBody();
        final Body bodyB = contact.getFixtureB()
                                  .getBody();
        try {
            return new EntityHolder(bodyA, bodyB);
        } catch (final ClassCastException | IllegalArgumentException e) {
            return null;
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
}
