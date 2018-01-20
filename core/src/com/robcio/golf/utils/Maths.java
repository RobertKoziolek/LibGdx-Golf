package com.robcio.golf.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Maths {

    final static private Random random = new Random();

    public static float nextFloat() {
        return random.nextFloat();
    }
}
