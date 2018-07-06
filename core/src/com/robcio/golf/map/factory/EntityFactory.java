package com.robcio.golf.map.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;

public abstract class EntityFactory {

    public abstract Entity create(final MapObject mapObject);

    float getRotation(final MapObject mapObject) {
        try {
            return (float) mapObject.getProperties().get("rotation");
        } catch (final NullPointerException e) {
            return 0f;
        }
    }
}
