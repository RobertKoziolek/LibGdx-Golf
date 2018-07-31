package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.robcio.golf.component.util.Selected;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;

public class SelectIndicatorAssigner implements EntityListener {

    final public static Family family = Family.all(Selected.class)
                                              .get();

    @Override
    public void entityAdded(final Entity entity) {
        final Sprite sprite = Assets.getSprite(TextureId.STAR);

        sprite.setOrigin(Selected.SIZE / 2f, Selected.SIZE / 2f);
        sprite.setSize(Selected.SIZE, Selected.SIZE);

        Mapper.selected.get(entity).sprite = sprite;
    }

    @Override
    public void entityRemoved(final Entity entity) {
        //nothing to do here
    }
}
