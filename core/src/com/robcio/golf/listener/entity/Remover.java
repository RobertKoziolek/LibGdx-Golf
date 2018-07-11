package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.utils.Mapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Remover implements EntityListener {

    final public static Family family = Family.all(ToRemove.class)
                                              .get();

    final private Engine engine;

    @Override
    public void entityAdded(final Entity entity) {
        final ToRemove toRemove = Mapper.toRemove.get(entity);
        remove(entity, toRemove);
    }

    private void remove(final Entity entity, final ToRemove toRemove) {
        entity.remove(ToRemove.class);
        if (toRemove.component != null) {
            entity.remove(toRemove.component);
        } else {
            engine.removeEntity(entity);
        }
    }

    @Override
    public void entityRemoved(final Entity entity) {
        //nothing to do here
    }
}
