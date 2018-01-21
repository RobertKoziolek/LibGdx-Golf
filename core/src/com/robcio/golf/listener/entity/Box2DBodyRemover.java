package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.utils.Log;
import com.robcio.golf.world.BodyDestroyer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Box2DBodyRemover implements EntityListener {
    final public static Family family = Family.all(Box2dBody.class).get();

    final private ComponentMapper<Box2dBody> b2dm = ComponentMapper.getFor(Box2dBody.class);

    final private BodyDestroyer bodyDestroyer;

    @Override
    public void entityAdded(Entity entity) {
        //nothing to do here
    }

    //TODO to trzeba stestowac kiedys czy poprawnie sie wykonuje
    @Override
    public void entityRemoved(Entity entity) {
        final Body body = b2dm.get(entity).body;
        bodyDestroyer.destroy(body);
    }
}
