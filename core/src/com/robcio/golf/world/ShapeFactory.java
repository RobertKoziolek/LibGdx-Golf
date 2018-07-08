package com.robcio.golf.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.utils.Maths;

public class ShapeFactory {

    public Shape createBox(Dimension dimension) {
        final PolygonShape shape = new PolygonShape();
        dimension = Dimension.radiusToBox2D(dimension);
        shape.setAsBox(dimension.width, dimension.height);

        return shape;
    }

    public Shape createCircular(Dimension dimension) {
        dimension = Dimension.radiusToBox2D(dimension);
        if (dimension.isSquare()) {
            return createCircle(dimension.width);
        } else {
            return createOval(dimension.width, dimension.height);
        }
    }

    private Shape createCircle(float radius) {
        final CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        return shape;
    }

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
        return Maths.getWorldVertices(vertices);
    }

    private Vector2[] getWorldVertices(final PolygonMapObject object) {
        final float[] vertices = object.getPolygon().getTransformedVertices();
        return Maths.getWorldVertices(vertices);
    }

    //TODO byc moze nalezaloby zwiekszyc ilosc verticesow w zaleznosci od wielkosci, bo kanciaste jest strasznie
    private Shape createOval(float radius1, float radius2) {
        final PolygonShape shape = new PolygonShape();
        final Vector2 vertices[] = new Vector2[8];
        for (int i = 0; i < 8; ++i)
            vertices[i] = new Vector2();
        final float dent = 0.7f;
        vertices[0].set(-radius1, 0);
        vertices[1].set(dent * -radius1, dent * radius2);
        vertices[2].set(0, radius2);
        vertices[3].set(dent * radius1, dent * radius2);
        vertices[4].set(radius1, 0);
        vertices[5].set(dent * radius1, dent * -radius2);
        vertices[6].set(0, -radius2);
        vertices[7].set(dent * -radius1, dent * -radius2);
        shape.set(vertices);
        return shape;
    }
}
