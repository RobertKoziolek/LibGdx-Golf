package com.robcio.golf.map;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.entity.Wall;
import com.robcio.golf.enumeration.MapId;
import com.robcio.golf.map.factory.EntityFactoryFacade;
import com.robcio.golf.utils.Log;
import com.robcio.golf.world.ShapeFactory;

public class MapReader {

    private final static String COLLISION_LAYER = "coll";
    private final static String ENTITY_LAYER = "entity";

    private final Engine engine;
    private final TmxMapLoader loader;
    private final EntityFactoryFacade entityFactoryFacade;
    private final ShapeFactory shapeFactory;

    public MapReader(final Engine engine) {
        this.engine = engine;
        this.loader = new TmxMapLoader();
        this.entityFactoryFacade = new EntityFactoryFacade();
        this.shapeFactory = new ShapeFactory();
    }

    public TiledMap load(final MapId map) {
        Log.i("Map loading", map.getName());
        final TiledMap loadedMap = loader.load("map/" + map.getFilename());
        parseTileMapLayerCollisions(loadedMap.getLayers()
                                             .get(COLLISION_LAYER)
                                             .getObjects());
        parseTileMapEntityObjects(loadedMap.getLayers()
                                           .get(ENTITY_LAYER)
                                           .getObjects());
        return loadedMap;
    }

    private void parseTileMapEntityObjects(final MapObjects mapObjects) {
        for (final MapObject object: mapObjects) {
            final Entity entity = entityFactoryFacade.create(object);
            engine.addEntity(entity);
        }
    }

    private void parseTileMapLayerCollisions(final MapObjects mapObjects) {
        for (MapObject object: mapObjects) {
            final Shape shape = shapeFactory.createShape(object);
            engine.addEntity(new Wall(shape));
        }
    }
}
