package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//TODO zrobic to moze na Bits od libgdx zeby mozna bylo listenowac na roznych rzeczach na raz
public enum EntityFlags {
    NONE(0), BALL(1), HOLE(2), BOWL(3), BUMPER(4), SLOPE(5), BOX(6), ROD(7);

    final private int id;

}
