package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class SpriteAssigner implements EntityListener {
    final public static Family family = Family.all(Renderable.class, Dimension.class).get();

    @Override
    public void entityAdded(Entity entity) {
        final Sprite sprite = Mapper.renderable.get(entity).sprite;
        final Dimension dimension = Mapper.dimension.get(entity);

        sprite.setOrigin(dimension.width / 2, dimension.height / 2);
        sprite.setSize(dimension.width, dimension.height);
    }

    @Override
    public void entityRemoved(Entity entity) {
        //nothing to do here
    }
}
