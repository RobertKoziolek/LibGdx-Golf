package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityFlags {
    NONE(0), BALL(1), HOLE(2), BOWL(3), BUMPER(4), SLOPE(5), BOX(6);

    final private int id;

}
