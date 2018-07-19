package com.robcio.golf.entity.graphics;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.graphics.FadeOut;
import com.robcio.golf.component.graphics.LightningSingleLine;
import com.robcio.golf.component.structure.Position;

//TODO przemyslec nazwy, moze oznaczac ze component w nazwie?
public class LightningLine extends Entity {

    public LightningLine(final Position start, final Position end) {
        add(LightningSingleLine.of(start, end));
        add(FadeOut.of(0.3f));
    }
}
