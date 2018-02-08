package com.robcio.golf.enumeration;

import com.badlogic.gdx.graphics.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  BallType {
    WHITE(Color.WHITE), BLACK(Color.BLACK), RED(Color.RED), GREEN(Color.GREEN);

    final private Color color;
}
