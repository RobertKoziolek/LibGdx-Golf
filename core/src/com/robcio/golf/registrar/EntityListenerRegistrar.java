package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.robcio.golf.listener.entity.*;
import com.robcio.golf.world.BodyDestroyer;


public class EntityListenerRegistrar {

    public EntityListenerRegistrar(final Engine engine, final BodyDestroyer bodyDestroyer) {
        engine.addEntityListener(SpriteAssigner.family, new SpriteAssigner());
        engine.addEntityListener(SelectIndicatorAssigner.family, new SelectIndicatorAssigner());
        engine.addEntityListener(Tinter.family, new Tinter());
        engine.addEntityListener(EntityRemover.family, new EntityRemover(engine));
        engine.addEntityListener(Box2DBodyRemover.family, new Box2DBodyRemover(bodyDestroyer));
    }
}