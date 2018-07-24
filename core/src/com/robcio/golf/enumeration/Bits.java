package com.robcio.golf.enumeration;

public class Bits {

    static public class C {
        public final static short WALL = 1;
        public final static short BALL = 2;
        public final static short BALL_MANIPULANT = 4;
        public final static short FREE_OBJECT = 8;
    }

    static public class M {
        public final static short BALL = C.BALL;
        public final static short FREE_OBJECT_AND_BALL = C.BALL + C.FREE_OBJECT;
        public final static short FREE_OBJECT_WILL_HIT = 15;
        public final static short BALL_WILL_HIT = 15;
    }

}
