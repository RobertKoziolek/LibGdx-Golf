package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.system.*;
import com.robcio.golf.system.control.AttractToSystem;
import com.robcio.golf.system.control.KickToSystem;
import com.robcio.golf.system.control.KickingSystem;
import com.robcio.golf.system.control.MoveSystem;
import com.robcio.golf.system.graphics.*;


public class EntitySystemRegistrar {

    public EntitySystemRegistrar(final Engine engine, final SpriteBatch batch, final Camera camera) {
        int priority = 0;
        engine.addSystem(new TimerSystem(priority++));

        engine.addSystem(new InBowlSystem(priority++));
        engine.addSystem(new SlopeSystem(priority++));

        engine.addSystem(new DispensingSystem(priority++));
        engine.addSystem(new ImpulseSystem(priority++));
        engine.addSystem(new HardImpulseSystem(priority++));
        engine.addSystem(new TrailingSystem(priority++));

        engine.addSystem(new MoveSystem(priority++));
        engine.addSystem(new KickingSystem(priority++));
        engine.addSystem(new KickToSystem(priority++));
        engine.addSystem(new AttractToSystem(priority++));

        engine.addSystem(new PositionSynchronizationSystem(priority++));

        engine.addSystem(new RenderSystem(priority++, batch));
        engine.addSystem(new SelectRenderSystem(priority++, batch));
        engine.addSystem(new LineRenderSystem(priority++, camera));
        engine.addSystem(new NotificationRenderSystem(priority++, batch));
    }
}