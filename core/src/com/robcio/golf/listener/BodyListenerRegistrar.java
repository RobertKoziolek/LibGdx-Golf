package com.robcio.golf.listener;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.box2d.BowlListener;
import com.robcio.golf.listener.box2d.BumperListener;
import com.robcio.golf.listener.box2d.HoleListener;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
class BodyListenerRegistrar {

    private final List<BodyListener> listeners = new LinkedList<>();

    BodyListenerRegistrar(final Engine engine) {
        listeners.add(new BowlListener());
        listeners.add(new HoleListener(engine));
        listeners.add(new BumperListener());
    }
}
