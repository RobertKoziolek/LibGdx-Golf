package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.robcio.golf.listener.input.PointerPosition;
import com.robcio.golf.system.control.KickToSystem;

public class KickTo extends AbstractMouseMode {

    public KickTo(final Engine engine, final PointerPosition pointerPosition) {
        super(engine, pointerPosition);
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
        pointerPosition.updateSelectionPoint();
        //TODO bzdura ze deltaTime podaje here zfixowany
        engine.getSystem(KickToSystem.class).update(100f);
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
