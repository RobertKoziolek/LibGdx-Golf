package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.entity.LightEntity;
import com.robcio.golf.listener.input.PointerPosition;

public class Creation extends TimedHoldableMouseMode {

    public Creation(final Integer shortcutKey, final Engine engine, final PointerPosition pointerPosition) {
        super(shortcutKey, engine, pointerPosition, 1f);
    }

    @Override
    protected void doWhenHolding() {
//        engine.addEntity(new Dispenser(pointerPosition.getUnprojectedPosition(), Dimension.of(18f), Force.of(2f)));
        engine.addEntity(new LightEntity(pointerPosition.getUnprojectedPosition()));
    }

    @Override
    public String getTooltip() {
        return "Creation";
    }
}
