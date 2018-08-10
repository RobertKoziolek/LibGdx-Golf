package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.util.Selected;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.listener.input.PointerPosition;
import com.robcio.golf.system.control.KickingSystem;

//TODO kreska od myszki dalej, moze raycast by pokazac w jaki obiekt trafi (jak ult Any w hotsie)
public class Kick extends AbstractMouseMode {

    public Kick(final Integer shortcutKey, final Engine engine, final PointerPosition pointerPosition) {
        super(shortcutKey, engine, pointerPosition);
    }

    @Override
    public void after() {
        deselectEverything();
    }

    @Override
    public boolean touchDown() {
        final Family family = Family.all(Position.class, Kickable.class).exclude(Selected.class).get();
        return select(family, true);
    }

    @Override
    public boolean touchUp() {
        updateSelectionPoint();
        updateSystem(KickingSystem.class);
        deselectEverything();
        return true;
    }

    @Override
    public boolean touchDragged() {
        return false;
    }

    @Override
    public Class<? extends EntitySystem> getSystemClass() {
        return KickingSystem.class;
    }

    @Override
    public String getTooltip() {
        return "Kick";
    }
}
