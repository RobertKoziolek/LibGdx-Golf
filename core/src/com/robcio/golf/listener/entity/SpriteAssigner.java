package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.MainClass;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Renderable;

public class SpriteAssigner implements EntityListener {
    final public static Family family = Family.all(Renderable.class, Dimension.class).get();

    final private ComponentMapper<Renderable> rm = ComponentMapper.getFor(Renderable.class);
    final private ComponentMapper<Dimension> dm = ComponentMapper.getFor(Dimension.class);

    @Override
    public void entityAdded(Entity entity) {
        final Sprite sprite = rm.get(entity).sprite;
        final Dimension dimension = dm.get(entity);

        sprite.setOrigin(dimension.width, dimension.height);
        sprite.setSize(dimension.width * 2, dimension.height * 2);
    }

    @Override
    public void entityRemoved(Entity entity) {
        //nothing to do here
    }
}
