package com.robcio.golf.entity.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.robcio.golf.component.graphics.LightningInfo;
import com.robcio.golf.component.structure.Position;
import lombok.NonNull;

public class Lightning extends Entity {

    private Lightning(final Position start, final Position end, final Color color) {
        add(LightningInfo.of(start, end, color));
    }

    public static Lightning of(@NonNull final Position start, @NonNull final Position end, @NonNull final Color color) {
        return new Lightning(start, end, color);
    }

    public static Lightning of(@NonNull final Position start, @NonNull final Position end) {
        return new Lightning(start, end, null);
    }

}
