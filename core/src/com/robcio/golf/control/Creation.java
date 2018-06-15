package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.listener.input.PointerPosition;

public class Creation extends AbstractMouseMode {

    public Creation(final Engine engine, final PointerPosition pointerPosition){
        super(engine, pointerPosition);
    }

    @Override
    public boolean touchDown() {
        engine.addEntity(new Ball(pointerPosition.getUnprojectedPosition(), Dimension.of(30), BallType.WHITE));
        return true;
    }

    @Override
    public String getTooltip() {
        return "Creation";
    }
}
