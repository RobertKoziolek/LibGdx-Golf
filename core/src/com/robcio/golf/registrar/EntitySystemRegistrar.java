package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.listener.entity.Box2DBodyRemover;
import com.robcio.golf.listener.entity.SpriteAssigner;
import com.robcio.golf.system.ImpulseSystem;
import com.robcio.golf.system.InBowlSystem;
import com.robcio.golf.system.RenderSystem;
import com.robcio.golf.world.BodyDestroyer;


public class EntitySystemRegistrar {

    public EntitySystemRegistrar(final Engine engine, final SpriteBatch batch) {
        engine.addSystem(new ImpulseSystem(3.5f));
        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new InBowlSystem());
    }
}