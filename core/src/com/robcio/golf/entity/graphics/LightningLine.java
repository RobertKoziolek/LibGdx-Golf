package com.robcio.golf.entity.graphics;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.graphics.LightningSingleLine;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.Timer;
import com.robcio.golf.component.util.ToRemove;

//TODO przemyslec nazwy, moze oznaczac ze component w nazwie?
public class LightningLine extends Entity {

    public LightningLine(final Position start, final Position end) {
        add(LightningSingleLine.of(start, end));
        add(Timer.of(0.2f, ToRemove.self()));
    }
}
