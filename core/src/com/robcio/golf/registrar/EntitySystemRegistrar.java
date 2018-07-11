package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.system.*;
import com.robcio.golf.system.control.AttractToSystem;
import com.robcio.golf.system.control.KickToSystem;
import com.robcio.golf.system.control.KickingSystem;
import com.robcio.golf.system.control.MoveSystem;
import com.robcio.golf.system.graphics.*;
import com.robcio.golf.system.util.LateCommandExecuter;

import java.util.LinkedList;
import java.util.List;


public class EntitySystemRegistrar {

    private final Engine engine;
    private final List<EntitySystem> systems = new LinkedList<>();

    public EntitySystemRegistrar(final Engine engine, final SpriteBatch batch, final Camera camera) {
        this.engine = engine;
        int priority = 0;
        add(new LateCommandExecuter(priority++));
        add(new TimerSystem(priority++));

        add(new InBowlSystem(priority++));
        add(new SlopeSystem(priority++));

        add(new DispensingSystem(priority++));
        add(new ImpulseSystem(priority++));
        add(new HardImpulseSystem(priority++));
        add(new TrailingSystem(priority++));

        add(new MoveSystem(priority++));
        add(new KickingSystem(priority++));
        add(new KickToSystem(priority++));
        add(new AttractToSystem(priority++));

        add(new PositionSynchronizationSystem(priority++));

       add(new RenderSystem(priority++, batch));
       add(new SelectRenderSystem(priority++, batch));
       add(new LineRenderSystem(priority++, camera));
       add(new MapRenderSystem(priority++, camera));
       add(new DebugRenderSystem(priority++));
       add(new NotificationRenderSystem(priority++, batch));
    }

    public void add(final EntitySystem system) {
        systems.add(system);
        engine.addSystem(system);
    }

    public void dispose() {
        for (final EntitySystem system: systems) {
            engine.removeSystem(system);
        }
    }
}