package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.flag.ToRemove;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityRemover implements EntityListener {
    
    final public static Family family = Family.all(ToRemove.class).get();

    final private Engine engine;

    @Override
    public void entityAdded(Entity entity) {
        engine.removeEntity(entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        //nothing to do here
    }
}
