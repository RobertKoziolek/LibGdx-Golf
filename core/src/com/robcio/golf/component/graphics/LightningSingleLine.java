package com.robcio.golf.component.graphics;

import com.robcio.golf.component.structure.Position;

public class LightningSingleLine extends Line {

    private LightningSingleLine(Position start, Position end) {
        super(start, end);
    }

    public static LightningSingleLine of(final Position start, final Position end) {
        return new LightningSingleLine(start, end);
    }
}
