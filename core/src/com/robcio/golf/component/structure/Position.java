package com.robcio.golf.component.structure;

import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.CloneableComponent;
import com.robcio.golf.utils.Maths;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Position implements CloneableComponent<Position> {
    public float x;
    public float y;

    public static Position of(final float x, final float y) {
        return new Position(x, y);
    }

    public static Position of(final Vector2 vector2) {
        return new Position(vector2.x, vector2.y);
    }

    public void set(final Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector2 getVector2() {
        return new Vector2(x, y);
    }

    @Override
    public Position clone() {
        return Position.of(x, y);
    }

    public static float distance(final Position position1, final Position position2) {
        final float x = position1.x - position2.x;
        final float y = position1.y - position2.y;
        return (float) Math.sqrt(x * x + y * y);
    }

    public static Position toBox2D(final Position position) {
        return Position.of(position.x / Maths.PPM, position.y / Maths.PPM);
    }

    public static Position fromBox2D(final Position position) {
        return Position.of(position.x * Maths.PPM, position.y * Maths.PPM);
    }

    public static Position mul(final Position position, final float fractor) {
        return Position.of(position.x * fractor, position.y * fractor);
    }

    public static Position div(final Position position, final int div) {
        return Position.of(position.x / div, position.y / div);
    }

    public static Position sub(final Position first, final Position second) {
        return Position.of(first.x - second.x, first.y - second.y);
    }

    public static Position add(final Position first, final Position second) {
        return Position.of(first.x + second.x, first.y + second.y);
    }

    public static Position nor (final Position position) {
        final Position normalized = position.clone();
        float len = len(normalized);
        if (len != 0) {
            normalized.x /= len;
            normalized.y /= len;
        }
        return normalized;
    }

    public static float len(final Position position) {
        return (float) Math.sqrt(position.x * position.x + position.y * position.y);
    }
}
