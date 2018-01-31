package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.system.SelectionSystem;
import com.robcio.golf.system.ImpulseSystem;
import com.robcio.golf.system.InBowlSystem;
import com.robcio.golf.system.RenderSystem;


public class EntitySystemRegistrar {

    public EntitySystemRegistrar(final Engine engine, final SpriteBatch batch) {
        engine.addSystem(new ImpulseSystem(1.0f));
        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new InBowlSystem());
        engine.addSystem(new SelectionSystem());
    }
}