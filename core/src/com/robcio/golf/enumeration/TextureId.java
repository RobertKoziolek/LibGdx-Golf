package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TextureId {

    GOLFBALL("golfball"),
    BUBBLE("bubble"),
    HOLE("hole"),
    BUMPER("bumper"),
    BOWL("bowl"),
    BOX("box"),
    SLOPE("slope"),
    STAR("star"),
    LIGHTNING_END("lightningend"),
    LIGHTNING("lightning");

    private String filename;
}
