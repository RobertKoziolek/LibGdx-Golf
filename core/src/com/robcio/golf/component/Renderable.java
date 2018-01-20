package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.utils.TextureManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Renderable implements Component {
    public Sprite sprite;

    public Renderable(final String path){
        sprite = new Sprite(TextureManager.get(path));
    }
}
