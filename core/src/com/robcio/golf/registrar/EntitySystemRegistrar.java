package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.system.*;
import com.robcio.golf.system.graphics.LineRenderSystem;
import com.robcio.golf.system.graphics.PositionSynchronizationSystem;
import com.robcio.golf.system.graphics.RenderSystem;


public class EntitySystemRegistrar {

    public EntitySystemRegistrar(final Engine engine, final SpriteBatch batch, final Camera camera) {
        int priority = 0;
        engine.addSystem(new InBowlSystem(priority++));
        engine.addSystem(new ImpulseSystem(priority++));
        engine.addSystem(new MoveSystem(priority++));
        engine.addSystem(new KickingSystem(priority++));
        engine.addSystem(new KickToSystem(priority++));
        engine.addSystem(new PositionSynchronizationSystem(priority++));
        engine.addSystem(new RenderSystem(priority++, batch));
        engine.addSystem(new LineRenderSystem(priority++, camera));
    }
}