package com.robcio.golf.enumeration;

import com.badlogic.gdx.graphics.Texture;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum TextureId {

    GOLFBALL("golfball.png"),
    BUBBLE("bubble.png"),
    HOLE("hole.png"),
    BUMPER("bumper.png"),
    BOWL("bowl.png"),
    BOX("box.png"),
    SLOPE("slope.png"),
    STAR("star.png");

    private String filename;
}
