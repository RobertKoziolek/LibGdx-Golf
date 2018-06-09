package com.robcio.golf.map;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.*;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.MapId;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Maths;
import com.robcio.golf.enumeration.TextureId;

public class MapReader {

    private final Engine engine;
    private final TmxMapLoader loader;
    private TiledMap current;
    private final EntityFactory entityFactory;

    public MapReader(final Engine engine) {
        this.engine = engine;
        this.loader = new TmxMapLoader();
        load(MapId.EMPTY);
        this.entityFactory = new EntityFactory();
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
            Shape shape;
            if (object instanceof PolygonMapObject) {
                shape = createPolygon(getWorldVertices((PolygonMapObject) object));
            } else if (object instanceof PolylineMapObject) {
                shape = createPolyLine(getWorldVertices((PolylineMapObject) object));
            } else {
                continue;
            }
            engine.addEntity(new Wall(shape));
            shape.dispose();
        }
    }

    private static Shape createPolygon(final Vector2[] worldVertices) {
        final PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

    private static ChainShape createPolyLine(final Vector2[] worldVertices) {
        final ChainShape shape = new ChainShape();
        shape.createChain(worldVertices);
        return shape;
    }

    private Vector2[] getWorldVertices(final PolylineMapObject object) {
        final float[] vertices = object.getPolyline().getTransformedVertices();
        return getWorldVertices(vertices);
    }

    private Vector2[] getWorldVertices(final PolygonMapObject object) {
        final float[] vertices = object.getPolygon().getTransformedVertices();
        return getWorldVertices(vertices);
    }

    private Vector2[] getWorldVertices(final float[] vertices) {
        final Vector2[] worldVertices = new Vector2[vertices.length / 2];
        for (int i = 0; i < worldVertices.length; ++i) {
            final Vector2 vector = new Vector2(vertices[i * 2], vertices[i * 2 + 1]);
            worldVertices[i] = vector.scl(1f / Maths.PPM);
        }
        return worldVertices;
    }

    public TiledMap getCurrentMap() {
        return current;
    }
}
