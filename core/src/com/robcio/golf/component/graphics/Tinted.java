package com.robcio.golf.component.graphics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import lombok.NonNull;

public class Tinted implements Component {
    public Color color;

    private Tinted(@NonNull final Color color){
        this.color = color;
    }

    public static Tinted of(final Color color) {
        return new Tinted(color);
    }
}
