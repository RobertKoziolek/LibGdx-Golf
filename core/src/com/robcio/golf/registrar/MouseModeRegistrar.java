package com.robcio.golf.registrar;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Input;
import com.robcio.golf.control.*;
import com.robcio.golf.listener.input.PointerPosition;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MouseModeRegistrar {

    private final List<MouseMode> mouseModes = new ArrayList<>();

    public MouseModeRegistrar(final Engine engine, final PointerPosition pointerPosition) {
        add(new AttractTo(Input.Keys.NUM_1, engine, pointerPosition));
        add(new Kick(Input.Keys.NUM_2, engine, pointerPosition));
        add(new Creation(Input.Keys.NUM_3, engine, pointerPosition));
        add(new KickTo(Input.Keys.NUM_4, engine, pointerPosition));
        add(new Motion(Input.Keys.NUM_5, engine, pointerPosition));
    }

    public MouseMode next(final MouseMode mouseMode) {
        final int indexOf = mouseModes.indexOf(mouseMode);
        return mouseModes.get(indexOf < mouseModes.size() - 1 ? indexOf + 1 : 0);
    }

    public MouseMode previous(final MouseMode mouseMode) {
        final int indexOf = mouseModes.indexOf(mouseMode);
        return mouseModes.get(indexOf == 0 ? mouseModes.size() - 1 : indexOf - 1);
    }

    private void add(final MouseMode mouseMode) {
        mouseModes.add(mouseMode);
    }
}
