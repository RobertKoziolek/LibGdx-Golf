package com.robcio.golf.utils;

import com.badlogic.gdx.math.MathUtils;
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

    public static float radiansToDegrees(final float angle) {
        return angle * MathUtils.radiansToDegrees;
    }
}
