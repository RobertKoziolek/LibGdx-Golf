package com.robcio.golf.component.flag;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.component.structure.Position;

public class Selected implements Component {
    static public final float SIZE = 16f;
    public static Position position = Position.of(0.f, 0.f);
    public Sprite sprite;
}
