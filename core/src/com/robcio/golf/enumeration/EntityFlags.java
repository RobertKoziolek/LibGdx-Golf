package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityFlags {
    NONE(0), BALL(1), HOLE(2);
    private int id;
}
