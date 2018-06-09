package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.system.*;


public class EntitySystemRegistrar {

    public EntitySystemRegistrar(final Engine engine, final SpriteBatch batch) {
        engine.addSystem(new ImpulseSystem());
        engine.addSystem(new PositionSynchronizationSystem());
        engine.addSystem(new InBowlSystem());
        engine.addSystem(new MoveSystem());
        engine.addSystem(new KickingSystem());
        engine.addSystem(new KickToSystem());
        engine.addSystem(new RenderSystem(batch));
    }
}