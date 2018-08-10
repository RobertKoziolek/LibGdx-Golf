package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Color;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.graphics.Lightning;
import com.robcio.golf.listener.input.PointerPosition;

public class LightningCreator extends TimedHoldableMouseMode {

    private float time;
    private Position firstPosition = Position.of(300f, 300f);

    public LightningCreator(final Integer shortcutKey, final Engine engine, final PointerPosition pointerPosition) {
        super(shortcutKey, engine, pointerPosition, 0.03f);
    }

    @Override
    public boolean touchUp() {
        super.touchUp();
        firstPosition = getUnprojectedPosition();
        return true;
    }

    @Override
    protected void doWhenHolding() {
        addEntity(Lightning.of(firstPosition, getUnprojectedPosition(), Color.SALMON));
    }

    @Override
    public String getTooltip() {
        return "Lightnings";
    }
}
