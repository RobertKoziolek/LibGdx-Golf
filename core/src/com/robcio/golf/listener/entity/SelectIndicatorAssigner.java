package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.Selected;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;

public class SelectIndicatorAssigner implements EntityListener {

    final public static Family family = Family.all(Renderable.class, Dimension.class, Selected.class).get();

    static private final float SELECT_SIZE = 16f;

    @Override
    public void entityAdded(Entity entity) {
        final Sprite sprite = new Sprite(Assets.Textures.get(TextureId.STAR));
        final Dimension dimension = Mapper.dimension.get(entity);

        final float radius = Math.min(dimension.getRadius1(), dimension.getRadius2());
        sprite.setOrigin(SELECT_SIZE / 2f, SELECT_SIZE / 2f);
        sprite.setSize(SELECT_SIZE, SELECT_SIZE);

        Mapper.selected.get(entity).sprite = sprite;
    }

    @Override
    public void entityRemoved(Entity entity) {
        //nothing to do here
    }
}
