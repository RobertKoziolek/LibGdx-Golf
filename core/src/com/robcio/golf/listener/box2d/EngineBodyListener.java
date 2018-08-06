package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class EngineBodyListener implements BodyListener {
    private final Engine engine;

    protected void addEntity(final Entity entity){
        engine.addEntity(entity);
    }
}
