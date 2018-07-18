package com.robcio.golf.entity.graphics;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.graphics.LightningInfo;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.Timer;
import com.robcio.golf.component.util.ToRemove;

public class Lightning extends Entity {

    public Lightning(final Position start, final Position end) {
        add(LightningInfo.of(start, end));
        add(Timer.of(0.4f, ToRemove.self()));
    }
}
