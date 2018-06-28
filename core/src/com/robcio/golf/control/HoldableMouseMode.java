package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.input.PointerPosition;

public abstract class HoldableMouseMode extends AbstractMouseMode {

    private boolean holding;

    public HoldableMouseMode(final Engine engine, final PointerPosition pointerPosition) {
        super(engine, pointerPosition);
    }

    @Override
    public void update() {
        if (holding) {
            doWhenHolding();
        }
    }

    protected abstract void doWhenHolding();

    @Override
    public boolean touchDown() {
        holding = true;
        return false;
    }

    @Override
    public boolean touchUp() {
        holding = false;
        return false;
    }
}
