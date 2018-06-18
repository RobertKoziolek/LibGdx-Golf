package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.flag.Selected;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.listener.input.PointerPosition;
import com.robcio.golf.system.MoveSystem;
import lombok.AllArgsConstructor;

public class Motion extends AbstractMouseMode {

    public Motion(final Engine engine, final PointerPosition pointerPosition) {
        super(engine, pointerPosition);
    }

    @Override
    public boolean touchDown() {
        final Family moveFamily = Family.all(Position.class, Selectable.class).exclude(Selected.class).get();
        return select(moveFamily, false);
    }

    @Override
    public boolean touchUp() {
        deselectEverything();
        return true;
    }

    @Override
    public boolean touchDragged() {
        pointerPosition.updateSelectionPoint();
        return true;
    }

    @Override
    public Class<? extends EntitySystem> getSystemClass() {
        return MoveSystem.class;
    }

    @Override
    public String getTooltip() {
        return "Motion";
    }
}
