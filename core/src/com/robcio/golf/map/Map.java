package com.robcio.golf.map;

import com.badlogic.ashley.core.Engine;
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
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Maths;
import com.robcio.golf.utils.Textures;

public class Map {
    private static final String mapPath = "map/map.tmx";

    private final Engine engine;
    private TiledMap tiledMap;

    public Map(final Engine engine) {
        this.engine = engine;

        this.tiledMap = new TmxMapLoader().load(mapPath);
        parseTileMapLayerCollisions(this.tiledMap.getLayers().get("coll").getObjects());
        parseTileMapBallObjects(this.tiledMap.getLayers().get("entity").getObjects());
    }

    private void parseTileMapBallObjects(final MapObjects mapObjects) {
        for (final MapObject object : mapObjects) {
            if (object instanceof EllipseMapObject) {
                final Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
                ellipse.x += ellipse.width / 2;
                ellipse.y += ellipse.height / 2;
                final String type = (String) object.getProperties().get("type");
                switch (type) {
                    case "bowl":
                        engine.addEntity(new Bowl(ellipse, Textures.BOWL));
                        break;
                    case "ball":
                        engine.addEntity(new Ball(ellipse));
                        break;
                    case "bumper":
                        engine.addEntity(new Bumper(ellipse));
                        break;
                    case "hole":
                        engine.addEntity(new Bowl(ellipse, Textures.HOLE));
                        engine.addEntity(new Hole(Position.of(ellipse.x, ellipse.y), Dimension.of(1f)));
                        break;
                    default:
                        throw new IllegalArgumentException(String.format("Map has an unknown Ellipse object type '%s'", type));
                }
            } else if (object instanceof RectangleMapObject) {
                final Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                rectangle.x += rectangle.width / 2;
                rectangle.y += rectangle.height / 2;
                final String type = (String) object.getProperties().get("type");
                switch (type) {
                    case "box":
                        engine.addEntity(new Box(rectangle));
                        break;
                    default:
                        throw new IllegalArgumentException(String.format("Map has an unknown Rectangle object type '%s'", type));
                }
            }
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
            worldVertices[i] = new Vector2(vertices[i * 2] / Maths.PPM, vertices[i * 2 + 1] / Maths.PPM);
        }
        return worldVertices;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }
}
