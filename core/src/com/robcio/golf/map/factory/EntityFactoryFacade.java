package com.robcio.golf.map.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class EntityFactoryFacade {

    private EntityFactory ellipseEntityFactory;

    private EntityFactory rectangleEntityFactory;

    public EntityFactoryFacade() {
        ellipseEntityFactory = new EllipseEntityFactory();
        rectangleEntityFactory = new RectangleEntityFactory();
    }

    public Entity create(final MapObject mapObject) {
        if (mapObject instanceof RectangleMapObject) {
            return rectangleEntityFactory.create(mapObject);
        } else if (mapObject instanceof EllipseMapObject) {
            return ellipseEntityFactory.create(mapObject);
        }
        throw new IllegalArgumentException("MapReader does not recognize map object type");
        //TODO zastanowic sie nad systemem bledow, zebrane w jednym miejscu, moze jakis log
    }
}
