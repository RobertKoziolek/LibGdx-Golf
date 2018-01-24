package com.robcio.golf.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.utils.Maths;


public class BodyFactory {
    private static World world;

    public static void setWorld(final World world) {
        if (BodyFactory.world != null) throw new IllegalStateException("World cannot be set twice");
        BodyFactory.world = world;
    }

    public static Body createBox(Position position, Dimension dimension, boolean isStatic,
                                 boolean isRotationFixed, int cbits, int mbits) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dimension.width / Maths.PPM / 2, dimension.height / Maths.PPM / 2);

        return getBody(position, shape, isStatic, isRotationFixed, cbits, mbits);
    }

    public static Body createCircular(Position position, Dimension dimension, boolean isStatic,
                                      boolean isRotationFixed, int cbits, int mbits) {
        if (dimension.isSquare()) {
            return createCircle(position, dimension.width, isStatic, isRotationFixed, cbits, mbits);
        } else {
            return createOval(position, dimension.width, dimension.height, isStatic, isRotationFixed, cbits, mbits);
        }
    }

    private static Body createCircle(Position position, float radius, boolean isStatic, boolean isRotationFixed,
                                     int cbits,
                                     int mbits) {
        CircleShape shape = new CircleShape();
        shape.setRadius(radius / Maths.PPM);
        return getBody(position, shape, isStatic, isRotationFixed, cbits, mbits);
    }

    private static Body createOval(Position position, float radius1, float radius2, boolean isStatic,
                                   boolean isRotationFixed,
                                   int cbits, int mbits) {
        PolygonShape shape = new PolygonShape();
        Vector2 vertices[] = new Vector2[8];
        for (int i = 0; i < 8; ++i)
            vertices[i] = new Vector2();
        radius1 /= Maths.PPM;
        radius2 /= Maths.PPM;
        float dent = 0.7f;
        vertices[0].set(-radius1, 0);
        vertices[1].set(dent * -radius1, dent * radius2);
        vertices[2].set(0, radius2);
        vertices[3].set(dent * radius1, dent * radius2);
        vertices[4].set(radius1, 0);
        vertices[5].set(dent * radius1, dent * -radius2);
        vertices[6].set(0, -radius2);
        vertices[7].set(dent * -radius1, dent * -radius2);
        shape.set(vertices);
        return getBody(position, shape, isStatic, isRotationFixed, cbits, mbits);
    }

    private static Body getBody(Position position, Shape shape, boolean isStatic, boolean isRotationFixed, int cbits,
                                int mbits) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = isRotationFixed;
        bodyDef.position.set(position.x / Maths.PPM, position.y / Maths.PPM);
        if (isStatic) {
            bodyDef.type = BodyType.StaticBody;
        } else {
            bodyDef.type = BodyType.DynamicBody;
        }
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = (short) cbits;
        fixtureDef.filter.maskBits = (short) mbits;
        Body tmpBody = world.createBody(bodyDef);
        tmpBody.createFixture(fixtureDef);
        shape.dispose();
        return tmpBody;
    }
}
