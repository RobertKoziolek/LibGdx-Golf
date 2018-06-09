package com.robcio.golf.map;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.entity.*;
import com.robcio.golf.enumeration.MapId;
import com.robcio.golf.map.factory.EntityFactory;
import com.robcio.golf.map.factory.ShapeFactory;
import com.robcio.golf.utils.Log;
import lombok.Getter;

public class MapReader {

    private final Engine engine;
    private final TmxMapLoader loader;
    @Getter
    private TiledMap current;
    private final com.robcio.golf.map.factory.EntityFactory entityFactory;
    private final com.robcio.golf.map.factory.ShapeFactory shapeFactory;

    public MapReader(final Engine engine) {
        this.engine = engine;
        this.loader = new TmxMapLoader();
        load(MapId.EMPTY);
        this.entityFactory = new EntityFactory();
        this.shapeFactory = new ShapeFactory();
    }

    public void load(final MapId map) {
        Log.i("Map loading", map.getName());
        this.current = loader.load("map/" + map.getFilename());
        if (map != MapId.EMPTY) {
            parseTileMapLayerCollisions(this.current.getLayers().get("coll").getObjects());
            parseTileMapBallObjects(this.current.getLayers().get("entity").getObjects());
        }
    }

    private void parseTileMapBallObjects(final MapObjects mapObjects) {
        for (final MapObject object : mapObjects) {
            final Entity entity = entityFactory.create(object);
            engine.addEntity(entity);
        }
    }

    private void parseTileMapLayerCollisions(final MapObjects mapObjects) {
        for (MapObject object : mapObjects) {
            final Shape shape = shapeFactory.createShape(object);
            engine.addEntity(new Wall(shape));
            shape.dispose();
        }
    }
}
