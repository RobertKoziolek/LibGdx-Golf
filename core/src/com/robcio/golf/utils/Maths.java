package com.robcio.golf.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Maths {

    final static private Random random = new Random();
    public static final float PPM = 64f;

    public static float nextFloat() {
        return random.nextFloat();
    }

    public static int nextInt(final int bound) {
        return random.nextInt(bound);
    }

    public static float radiansToDegrees(final float angle) {
        return angle * MathUtils.radiansToDegrees;
    }

    public static float degreesToRadians(final float angle) {
        return angle * MathUtils.degreesToRadians;
    }

    //TODO eee to jest tylko po to by pierwszy vector sei nei zmnienil
    public static Vector2 getDistance(final Vector2 vector1, final Vector2 vector2) {
        return vector1.cpy().sub(vector2);
    }

    public static Vector2[] getWorldVertices(final float[] vertices) {
        final Vector2[] worldVertices = new Vector2[vertices.length / 2];
        for (int i = 0; i < worldVertices.length; ++i) {
            final Vector2 vector = new Vector2(vertices[i * 2], vertices[i * 2 + 1]);
            worldVertices[i] = vector.scl(1f / Maths.PPM);
        }
        return worldVertices;
    }

    public static Vector2 inverse(final Vector2 vector) {
        return vector.scl(-1f);
    }

    public static float getVectorSum(final Vector2 vector) {
        return Math.abs(vector.x) + Math.abs(vector.y);
    }

    public static <T> T getRandom(T[] values) {
        return values[random.nextInt(values.length)];
    }

    public static float random(float min, float max) {
        return (random.nextFloat() * (max - min)) + min;
    }
}
