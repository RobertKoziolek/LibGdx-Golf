package com.robcio.golf.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Maths {

    final static private Random random = new Random();
    public static final float PPM = 64;

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

    public static Vector2 getDistance(final Vector2 vector1, final Vector2 vector2) {
        Vector2 distance = Vector2.Zero.cpy();
        distance.add(vector1);
        distance.sub(vector2);
        return distance;
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
}
