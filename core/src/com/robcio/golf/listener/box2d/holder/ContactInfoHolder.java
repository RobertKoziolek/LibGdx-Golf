package com.robcio.golf.listener.box2d.holder;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.enumeration.EntityFlags;

public interface ContactInfoHolder {

    Entity getA();
    Entity getB();
    Body getBodyA();
    Body getBodyB();

    boolean containsFlags(final EntityFlags[] flagsA, final EntityFlags[] flagsB);

    boolean isEmpty();
}
