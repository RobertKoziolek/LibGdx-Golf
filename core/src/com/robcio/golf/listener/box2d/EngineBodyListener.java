package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.BodyListener;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class EngineBodyListener implements BodyListener {
    protected final Engine engine;
}
