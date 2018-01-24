package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.utils.Textures;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Renderable implements Component {
    public Sprite sprite;
    public int z;

    public static Renderable of(final String path) {
        return of(path, 10);
    }

    public static Renderable of(final String path, final int z) {
        final Renderable renderable = new Renderable();
        renderable.sprite = new Sprite(Textures.get(path));
        renderable.z = z;
        return renderable;
    }
}
