package com.robcio.golf.component.graphics;

import com.badlogic.ashley.core.Component;
import com.robcio.golf.component.structure.Position;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LightningInfo implements Component {
    public Position source;
    public Position destination;

    public static LightningInfo of(final Position source, final Position destination) {
        return new LightningInfo(source, destination);
    }
}
