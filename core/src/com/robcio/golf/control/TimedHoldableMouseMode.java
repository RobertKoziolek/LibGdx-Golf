package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.input.PointerPosition;

public abstract class TimedHoldableMouseMode extends HoldableMouseMode {

    private final float thresholdTime;

    private float time;

    public TimedHoldableMouseMode(final Integer shorcutKey,
                                  final Engine engine,
                                  final PointerPosition pointerPosition,
                                  final float thresholdTime) {
        super(shorcutKey, engine, pointerPosition);
        this.thresholdTime = thresholdTime;
    }

    @Override
    protected final void doWhenHolding(final float deltaTime) {
        if ((time += deltaTime) > thresholdTime) {
            doWhenHolding();
            time = 0f;
        }
    }

    protected abstract void doWhenHolding();
}
