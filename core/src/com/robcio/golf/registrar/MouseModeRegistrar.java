package com.robcio.golf.registrar;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.control.*;
import com.robcio.golf.listener.input.PointerPosition;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MouseModeRegistrar {

    private final List<MouseMode> mouseModes = new ArrayList<>();

    public MouseModeRegistrar(final Engine engine, final PointerPosition pointerPosition) {
        //TODO pierwszy jesli ma do zrobienie before to go nie robi
        add(new Creation(engine, pointerPosition));
        add(new Kick(engine, pointerPosition));
        add(new KickTo(engine, pointerPosition));
        add(new AttractTo(engine, pointerPosition));
        add(new Motion(engine, pointerPosition));
    }

    public MouseMode next(final MouseMode mouseMode) {
        final int indexOf = mouseModes.indexOf(mouseMode);
        return mouseModes.get(indexOf < mouseModes.size() - 1 ? indexOf + 1 : 0);
    }

    private void add(final MouseMode mouseMode) {
        mouseModes.add(mouseMode);
    }
}
