package com.robcio.golf.registrar;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.box2d.BodyListener;
import com.robcio.golf.listener.box2d.listener.*;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class BodyListenerRegistrar {

    private final List<BodyListener> listeners = new LinkedList<>();

    //TODO NOTIF zastanowic sie czy w ten sposob chcemy zalaczac notyfikacje, glupio tak przerzucac engine wszedzie
    public BodyListenerRegistrar(final Engine engine) {
        listeners.add(new RodListener());
        listeners.add(new BowlListener());
        listeners.add(new HoleListener(engine));
        listeners.add(new LaunchPadListener());
        listeners.add(new BumperListener(engine));
        listeners.add(new FireSpreaderListener());
        listeners.add(new SlopeListener());
    }
}
