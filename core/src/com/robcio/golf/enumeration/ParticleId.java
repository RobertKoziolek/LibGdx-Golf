package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParticleId {

    FIRE("particles/fire.p");

    private String filename;
}
