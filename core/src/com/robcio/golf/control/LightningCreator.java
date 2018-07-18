package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.graphics.Lightning;
import com.robcio.golf.listener.input.PointerPosition;

public class LightningCreator extends HoldableMouseMode {

    private short timer;
    private Position firstPosition = Position.of(300f,300f);

    public LightningCreator(final Integer shortcutKey, final Engine engine, final PointerPosition pointerPosition) {
        super(shortcutKey, engine, pointerPosition);
    }

    @Override
    public boolean touchUp() {
        super.touchUp();
        firstPosition = pointerPosition.getUnprojectedPosition();
        return true;
    }

    @Override
    protected void doWhenHolding(final float deltaTime) {
        timer++;
        if (timer > 2) {
            engine.addEntity(new Lightning(firstPosition, pointerPosition.getUnprojectedPosition()));
            timer = 0;
        }
    }

    @Override
    public String getTooltip() {
        return "Lightnings";
    }
}
