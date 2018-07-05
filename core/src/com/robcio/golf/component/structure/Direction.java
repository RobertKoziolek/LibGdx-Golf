package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import lombok.NonNull;

public class Direction implements Component {
    public Vector2 value;

    public Direction(@NonNull final Vector2 value) {
        this.value = value;
    }

    public static Direction of(final String directionString) {
        switch (directionString) {
            case "left":
                return new Direction(new Vector2(-1f, 0f));
            case "right":
                return new Direction(new Vector2(1f, 0f));
            case "up":
                return new Direction(new Vector2(0f, 1f));
            case "down":
                return new Direction(new Vector2(0f, -1f));
            default:
                throw new IllegalArgumentException("Direction not recognized");
        }
    }
}
