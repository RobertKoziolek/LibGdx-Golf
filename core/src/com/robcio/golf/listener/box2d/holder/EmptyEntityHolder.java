package com.robcio.golf.listener.box2d.holder;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.enumeration.EntityFlags;

public class EmptyEntityHolder implements ContactInfoHolder{

    private static final String ENTITY_HOLDER_SHOULD_NOT_BE_USED = "Empty entity holder should not be used";

    public EmptyEntityHolder() {
    }

    @Override
    public Entity getA() {
        throw new IllegalStateException(ENTITY_HOLDER_SHOULD_NOT_BE_USED);
    }

    @Override
    public Entity getB() {
        throw new IllegalStateException(ENTITY_HOLDER_SHOULD_NOT_BE_USED);
    }

    @Override
    public Body getBodyA() {
        throw new IllegalStateException(ENTITY_HOLDER_SHOULD_NOT_BE_USED);
    }

    @Override
    public Body getBodyB() {
        throw new IllegalStateException(ENTITY_HOLDER_SHOULD_NOT_BE_USED);
    }

    public boolean containsFlags(final EntityFlags[] flagsA, final EntityFlags[] flagsB) {
        throw new IllegalStateException(ENTITY_HOLDER_SHOULD_NOT_BE_USED);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
