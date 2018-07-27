package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.system.DispensingSystem;
import com.robcio.golf.system.LightningTetherSystem;
import com.robcio.golf.system.TrailingSystem;
import com.robcio.golf.system.control.AttractToSystem;
import com.robcio.golf.system.control.KickToSystem;
import com.robcio.golf.system.control.KickingSystem;
import com.robcio.golf.system.control.MoveSystem;
import com.robcio.golf.system.graphics.*;
import com.robcio.golf.system.physics.*;
import com.robcio.golf.system.util.LateCommandExecuter;
import com.robcio.golf.system.util.TimerSystem;

import java.util.LinkedList;
import java.util.List;


public class EntitySystemRegistrar {

    private final Engine engine;
    private final List<EntitySystem> systems = new LinkedList<>();

    public EntitySystemRegistrar(final Engine engine,
                                 final World world,
                                 final SpriteBatch batch,
                                 final Camera camera) {
        this.engine = engine;
        int priority = 0;
        add(new TimerSystem(priority++));
        //TODO lateCommand musi byc po tajmerze, chce wiedziec czemu?
        add(new LateCommandExecuter(priority++));

        add(new InBowlSystem(priority++));
        add(new SlopeSystem(priority++));

        add(new BodyFilterChangingSystem(priority++));

        add(new LightningTetherSystem(priority++, world));
        add(new DispensingSystem(priority++));
        add(new ImpulseSystem(priority++));
        add(new HardImpulseSystem(priority++));
        add(new TrailingSystem(priority++));

        add(new MoveSystem(priority++));
        add(new KickingSystem(priority++));
        add(new KickToSystem(priority++));
        add(new AttractToSystem(priority++));

        add(new PositionSynchronizationSystem(priority++));

        add(new FadeOutSystem(priority++));
        add(new RenderSystem(priority++, batch));
        add(new ParticleRenderSystem(priority++, batch));
        add(new LightningRenderSystem(priority++, batch));
        add(new SelectRenderSystem(priority++, batch));
        add(new MapRenderSystem(priority++, camera));
        add(new DebugRenderSystem(priority++));
        add(new LineRenderSystem(priority++, camera));
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