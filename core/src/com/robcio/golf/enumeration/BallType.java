package com.robcio.golf.enumeration;

import com.badlogic.gdx.graphics.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BallType {
    WHITE(Color.WHITE), BLACK(Color.BLACK), RED(Color.RED), GREEN(Color.GREEN), BLUE(Color.BLUE), BROWN(
            Color.BROWN), CORAL(Color.CORAL), FOREST(Color.FOREST), FIREBRICK(Color.FIREBRICK), PINK(
            Color.PINK), MAROON(Color.MAROON), LIME(Color.LIME), MAGENTA(Color.MAGENTA), GOLD(Color.GOLD);

    final private Color color;
}
