package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.entity.Dispenser;
import com.robcio.golf.listener.input.PointerPosition;

public class Creation extends HoldableMouseMode {

    private short timer;

    public Creation(final Integer shortcutKey, final Engine engine, final PointerPosition pointerPosition) {
        super(shortcutKey, engine, pointerPosition);
    }

    @Override
    protected void doWhenHolding(final float deltaTime) {
        timer++;
        if (timer > 4) {
            engine.addEntity(new Dispenser(pointerPosition.getUnprojectedPosition(), Dimension.of(18f), Force.of(2f)));
            timer = 0;
        }
    }

    @Override
    public String getTooltip() {
        return "Creation";
    }
}
