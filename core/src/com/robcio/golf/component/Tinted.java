package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;

public class Tinted implements Component {
    public Color color;

    private Tinted(final Color color){
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        this.color = color;
    }

    public static Tinted of(final Color color) {
        return new Tinted(color);
    }
}
