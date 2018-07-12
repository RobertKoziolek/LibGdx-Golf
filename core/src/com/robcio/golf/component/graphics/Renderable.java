package com.robcio.golf.component.graphics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Assets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Renderable implements Component {
    public Sprite sprite;
    public int z;

    public static Renderable of(final TextureId textureId) {
        return of(textureId, 10);
    }

    public static Renderable of(final TextureId textureId, final int z) {
        final Renderable renderable = new Renderable();
        renderable.sprite = Assets.getSprite(textureId);
        renderable.z = z;
        return renderable;
    }
}
