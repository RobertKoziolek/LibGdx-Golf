package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.input.PointerPosition;

public abstract class HoldableMouseMode extends AbstractMouseMode {

    private boolean holding;

    public HoldableMouseMode(final Integer shorcutKey, final Engine engine, final PointerPosition pointerPosition) {
        super(shorcutKey, engine, pointerPosition);
    }

    @Override
    public void update(final float deltaTime) {
        if (holding) {
            doWhenHolding(deltaTime);
        }
    }

    protected abstract void doWhenHolding(final float deltaTime);

    @Override
    public boolean touchDown() {
        holding = true;
        return super.touchDown();
    }

    @Override
    public boolean touchUp() {
        holding = false;
        return super.touchUp();
    }
}
