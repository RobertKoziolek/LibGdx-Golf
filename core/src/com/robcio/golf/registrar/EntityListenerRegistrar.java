package com.robcio.golf.registrar;


import box2dLight.RayHandler;
import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.entity.*;
import com.robcio.golf.listener.entity.creation.HoleCreationListener;
import com.robcio.golf.listener.entity.creation.RodCreationListener;
import com.robcio.golf.world.BodyDestroyer;


public class EntityListenerRegistrar {

    public EntityListenerRegistrar(final Engine engine,
                                   final BodyDestroyer bodyDestroyer,
                                   final RayHandler rayHandler) {
        int priority = 0;
        engine.addEntityListener(MapLoader.family, priority++, new MapLoader(engine));
        engine.addEntityListener(LightListener.family, priority++, new LightListener(rayHandler));
        engine.addEntityListener(SpriteAssigner.family, priority++, new SpriteAssigner());
        engine.addEntityListener(SelectIndicatorAssigner.family, priority++, new SelectIndicatorAssigner());
        engine.addEntityListener(Tinter.family, priority++, new Tinter());
        engine.addEntityListener(Remover.family, priority++, new Remover(engine));
        engine.addEntityListener(Box2DBodyRemover.family, priority++, new Box2DBodyRemover(bodyDestroyer));
        engine.addEntityListener(HoleCreationListener.family, priority++, new HoleCreationListener(engine));
        engine.addEntityListener(RodCreationListener.family, priority++, new RodCreationListener(engine));
        engine.addEntityListener(ParticleLoader.family, priority++, new ParticleLoader());
        engine.addEntityListener(LightningSplitter.family, priority++, new LightningSplitter(engine));
    }
}