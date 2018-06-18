package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.utils.Maths;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Line implements Component {
    public Position start;
    public Position end;

    public static Line of(final Position start, final Position end) {
        return new Line(start, end);
    }
}
