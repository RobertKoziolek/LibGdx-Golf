package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.graphics.Renderable;
import com.robcio.golf.component.util.Selected;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;

public class SelectIndicatorAssigner implements EntityListener {

    final public static Family family = Family.all(Renderable.class, Dimension.class, Selected.class).get();

    @Override
    public void entityAdded(Entity entity) {
        final Sprite sprite = new Sprite(Assets.Textures.get(TextureId.STAR));
        final Dimension dimension = Mapper.dimension.get(entity);

        final float radius = Math.min(dimension.getRadiusX(), dimension.getRadiusY());
        sprite.setOrigin(Selected.SIZE / 2f, Selected.SIZE / 2f);
        sprite.setSize(Selected.SIZE, Selected.SIZE);

        Mapper.selected.get(entity).sprite = sprite;
    }

    @Override
    public void entityRemoved(Entity entity) {
        //nothing to do here
    }
}
