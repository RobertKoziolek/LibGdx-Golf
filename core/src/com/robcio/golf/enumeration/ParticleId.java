package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParticleId {

    FIRE("particles/fire.p"),
    WATER("particles/water.p");

    private String filename;
}
