package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.world.BodyDestroyer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Box2DBodyRemover implements EntityListener {
    final public static Family family = Family.all(Box2dBody.class).get();

    final private BodyDestroyer bodyDestroyer;

    @Override
    public void entityAdded(Entity entity) {
        //nothing to do here
    }

    @Override
    public void entityRemoved(Entity entity) {
        final Body body = Mapper.box2dBody.get(entity).body;
        bodyDestroyer.destroy(body);
    }
}
