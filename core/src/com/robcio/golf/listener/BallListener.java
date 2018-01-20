package com.robcio.golf.listener;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.MainClass;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Renderable;

public class BallListener implements EntityListener {
    final private ComponentMapper<Box2dBody> b2dm = ComponentMapper.getFor(Box2dBody.class);
    final private ComponentMapper<Renderable> rm = ComponentMapper.getFor(Renderable.class);

    @Override
    public void entityAdded(Entity entity) {
        final Body body = b2dm.get(entity).body;
        final Sprite sprite = rm.get(entity).sprite;
        final float radius = body.getFixtureList().get(0).getShape().getRadius() * MainClass.PPM;
        sprite.setOrigin(radius, radius);
        sprite.setSize(radius * 2, radius * 2);
    }

    @Override
    public void entityRemoved(Entity entity) {
    }
}
