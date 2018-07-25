package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.graphics.Renderable;
import com.robcio.golf.utils.Mapper;

public class SpriteAssigner implements EntityListener {

    final public static Family family = Family.all(Renderable.class, Dimension.class).get();

    @Override
    public void entityAdded(final Entity entity) {
        final Sprite sprite = Mapper.renderable.get(entity).sprite;
        final Dimension dimension = Mapper.dimension.get(entity);

        sprite.setOrigin(dimension.getRadiusX(), dimension.getRadiusY());
        sprite.setSize(dimension.width, dimension.height);
    }

    @Override
    public void entityRemoved(final Entity entity) {
        //nothing to do here
    }
}
