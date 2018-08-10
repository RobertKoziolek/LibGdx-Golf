package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.util.Timer;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.entity.light.LightEntity;
import com.robcio.golf.listener.input.PointerPosition;

public class Creation extends TimedHoldableMouseMode {

    public Creation(final Integer shortcutKey, final Engine engine, final PointerPosition pointerPosition) {
        super(shortcutKey, engine, pointerPosition, 1f);
    }

    @Override
    protected void doWhenHolding() {
//        engine.addEntity(new Dispenser(pointerPosition.getUnprojectedPosition(), Dimension.of(18f), Force.of(2f)));
        final LightEntity lightEntity = new LightEntity(getUnprojectedPosition(), Force.of(5f));
        lightEntity.add(Timer.of(3f, ToRemove.self()));
        addEntity(lightEntity);
    }

    @Override
    public String getTooltip() {
        return "Creation";
    }
}
