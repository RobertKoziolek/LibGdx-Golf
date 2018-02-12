package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.component.Selected;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;

public class SelectIndicatorAssigner implements EntityListener {
    final public static Family family = Family.all(Renderable.class, Dimension.class, Selected.class).get();

    @Override
    public void entityAdded(Entity entity) {
        final Sprite sprite = new Sprite(Assets.Textures.get(TextureId.STAR));
        final Dimension dimension = Mapper.dimension.get(entity);

        sprite.setOrigin(dimension.getRadius1() / 2f, dimension.getRadius2() / 2f);
        sprite.setSize(dimension.width / 2f, dimension.height / 2f);

        Mapper.selected.get(entity).sprite = sprite;
    }

    @Override
    public void entityRemoved(Entity entity) {
        //nothing to do here
    }
}
