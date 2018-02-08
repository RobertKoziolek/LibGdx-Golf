package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.robcio.golf.enumeration.BallType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tinted implements Component {
    public Color color;

    public static Tinted of(final Color color) {
        return new Tinted(color);
    }
}
