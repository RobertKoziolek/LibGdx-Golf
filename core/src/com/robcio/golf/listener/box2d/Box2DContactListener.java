package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.physics.box2d.*;
import com.robcio.golf.listener.box2d.holder.ContactInfoHolder;
import com.robcio.golf.listener.box2d.holder.HolderFactory;
import com.robcio.golf.registrar.BodyListenerRegistrar;

public class Box2DContactListener implements ContactListener {

    final private BodyListenerRegistrar registrar;

    public Box2DContactListener(final Engine engine) {
        this.registrar = new BodyListenerRegistrar(engine);
    }

    @Override
    public void beginContact(final Contact contact) {
        final ContactInfoHolder contactInfoHolder = HolderFactory.create(contact);
        if (contactInfoHolder.isEmpty()) return;

        for (final BodyListener listener: registrar.getListeners()) {
            if (contactInfoHolder.containsFlags(listener.getEntityFlagsA(), listener.getEntityFlagsB())) {
                listener.beginContact(contactInfoHolder);
            }
        }
    }

    @Override
    public void endContact(final Contact contact) {
        final ContactInfoHolder contactInfoHolder = HolderFactory.create(contact);
        if (contactInfoHolder.isEmpty()) return;

        for (final BodyListener listener: registrar.getListeners()) {
            if (contactInfoHolder.containsFlags(listener.getEntityFlagsA(), listener.getEntityFlagsB())) {
                listener.endContact(contactInfoHolder);
            }
        }
    }

    @Override
    public void preSolve(final Contact contact, final Manifold oldManifold) {
        //nothing to do here
    }

    //TODO to moze miec sens to ogarniecia bumpera skoro jest impuls kontaktu here
    @Override
    public void postSolve(final Contact contact, final ContactImpulse impulse) {
        //nothing to do here
    }
}
