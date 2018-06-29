package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.robcio.golf.listener.input.PointerPosition;
import com.robcio.golf.system.control.AttractToSystem;

public class AttractTo extends HoldableMouseMode {

    public AttractTo(final Engine engine, final PointerPosition pointerPosition) {
        super(engine, pointerPosition);
    }

    @Override
    protected void doWhenHolding(final float deltaTime) {
        pointerPosition.updateSelectionPoint();
        engine.getSystem(AttractToSystem.class).update(deltaTime);
    }

    @Override
    public void before() {
        selectEverything();
    }

    @Override
    public void after() {
        deselectEverything();
    }

    @Override
    public Class<? extends EntitySystem> getSystemClass() {
        return AttractToSystem.class;
    }

    @Override
    public String getTooltip() {
        return "AttractTo";
    }
}
