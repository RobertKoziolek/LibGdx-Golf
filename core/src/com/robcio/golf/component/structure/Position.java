package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.utils.Maths;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Position implements Component {
    public float x = 0.0f;
    public float y = 0.0f;

    public static Position of(final float x, final float y) {
        return new Position(x, y);
    }

    public static Position of(final Vector2 vector2) {
        return new Position(vector2.x, vector2.y);
    }

    public static float distance(final Position position1, final Position position2) {
        final float x = position1.x - position2.x;
        final float y = position1.y - position2.y;
        return (float) Math.sqrt(x * x + y * y);
    }

    public static Position toBox2D(final Position position) {
        return Position.of(position.x / Maths.PPM, position.y / Maths.PPM);
    }

    public static Position fromBox2D(final Position position){
        return Position.of(position.x * Maths.PPM, position.y * Maths.PPM);
    }

    public void set(final Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector2 getVector2() {
        return new Vector2(x, y);
    }
}
