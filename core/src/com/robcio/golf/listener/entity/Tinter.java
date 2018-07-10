package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.graphics.Renderable;
import com.robcio.golf.component.graphics.Tinted;
import com.robcio.golf.utils.Mapper;

public class Tinter implements EntityListener {

    final public static Family family = Family.all(Renderable.class, Dimension.class, Tinted.class).get();

    @Override
    public void entityAdded(Entity entity) {
        final Sprite sprite = Mapper.renderable.get(entity).sprite;
        final Color tint = Mapper.tinted.get(entity).color;
        sprite.setColor(tint);
    }

    @Override
    public void entityRemoved(Entity entity) {
        //nothing to do here
    }
}
