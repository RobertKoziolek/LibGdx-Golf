package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Selected implements Component {
    public static Position position = Position.of(0.f, 0.f);
    public Sprite sprite;
}
