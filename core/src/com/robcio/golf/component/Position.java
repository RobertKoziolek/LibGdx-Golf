package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Position implements Component {
    public float x = 0.0f;
    public float y = 0.0f;

    public static Position of(final float x, final float y) {
        return new Position(x, y);
    }

    public void set(final Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public static float distance(final Position position1, final Position position2) {
        final float x = position1.x - position2.x;
        final float y = position1.y - position2.y;
        return (float) Math.sqrt(x * x + y * y);
    }
}
