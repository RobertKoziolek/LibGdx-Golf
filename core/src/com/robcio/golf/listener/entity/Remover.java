package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.flag.ToRemove;
import com.robcio.golf.utils.Mapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Remover implements EntityListener {

    final public static Family family = Family.all(ToRemove.class).get();

    final private Engine engine;

    @Override
    public void entityAdded(Entity entity) {
        final ToRemove toRemove = Mapper.toRemove.get(entity);
        if (toRemove.component != null) {
            entity.remove(toRemove.component);
            entity.remove(ToRemove.class);
        } else {
            engine.removeEntity(entity);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
        //nothing to do here
    }
}