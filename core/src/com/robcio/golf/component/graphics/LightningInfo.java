package com.robcio.golf.component.graphics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.robcio.golf.component.structure.Position;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LightningInfo implements Component {
    final public Position source;
    final public Position destination;
    final public Color color;

    public static LightningInfo of(@NonNull final Position source,
                                   @NonNull final Position destination,
                                   final Color color) {
        return new LightningInfo(source, destination, color);
    }
}
