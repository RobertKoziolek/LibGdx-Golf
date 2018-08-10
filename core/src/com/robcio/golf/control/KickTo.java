package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.robcio.golf.listener.input.PointerPosition;
import com.robcio.golf.system.control.KickToSystem;

public class KickTo extends AbstractMouseMode {

    public KickTo(final Integer shortcutKey, final Engine engine, final PointerPosition pointerPosition) {
        super(shortcutKey, engine, pointerPosition);
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
    public boolean touchUp() {
        updateSelectionPoint();
        updateSystem(KickToSystem.class);
        return true;
    }

    @Override
    public Class<? extends EntitySystem> getSystemClass() {
        return KickToSystem.class;
    }

    @Override
    public String getTooltip() {
        return "KickTo";
    }
}
