package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Impulse implements Component {
    public Vector2 impulse;
}
