package com.robcio.golf.utils;

import com.badlogic.gdx.graphics.Texture;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Textures {
    final public static String GOLFBALL = "golfball.png";
    final public static String HOLE = "hole.png";
    final public static String BUMPER = "bumper.png";
    final public static String BOWL = "bowl.png";

    final private static Map<String, Texture> textures = new HashMap();

    public static Texture get(final String path) {
        if (textures.containsKey(path)){
            return textures.get(path);
        }
        else {
            final Texture texture = new Texture(path);
            textures.put(path, texture);
            return texture;
        }
    }

    public static void dispose(){
        for (Texture texture : textures.values()){
            texture.dispose();
        }
    }
}
