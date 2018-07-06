package com.robcio.golf.map.factory;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.utils.Maths;

public class ShapeFactory {

    public Shape createShape(final MapObject mapObject) {
        if (mapObject instanceof PolygonMapObject) {
            return createPolygon(getWorldVertices((PolygonMapObject) mapObject));
        } else if (mapObject instanceof PolylineMapObject) {
            return createPolyLine(getWorldVertices((PolylineMapObject) mapObject));
        }
        throw new IllegalArgumentException("Map reader can not recognize wall object type");
    }

    private Shape createPolygon(final Vector2[] worldVertices) {
        final PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

    private ChainShape createPolyLine(final Vector2[] worldVertices) {
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
}
