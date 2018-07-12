package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.entity.*;
import com.robcio.golf.world.BodyDestroyer;


public class EntityListenerRegistrar {

    public EntityListenerRegistrar(final Engine engine, final BodyDestroyer bodyDestroyer) {
        int priority = 0;
        engine.addEntityListener(MapLoader.family, priority++, new MapLoader(engine));
        engine.addEntityListener(SpriteAssigner.family, priority++, new SpriteAssigner());
        engine.addEntityListener(SelectIndicatorAssigner.family, priority++, new SelectIndicatorAssigner());
        engine.addEntityListener(Tinter.family, priority++, new Tinter());
        engine.addEntityListener(Remover.family, priority++, new Remover(engine));
        engine.addEntityListener(Box2DBodyRemover.family, priority++, new Box2DBodyRemover(bodyDestroyer));
        engine.addEntityListener(HoleCreationListener.family, priority++, new HoleCreationListener(engine));
        engine.addEntityListener(ParticleLoader.family, priority++, new ParticleLoader());
    }
}