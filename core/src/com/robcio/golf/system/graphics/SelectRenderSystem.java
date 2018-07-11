package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.Selected;
import com.robcio.golf.utils.Mapper;

public class SelectRenderSystem extends BatchIteratingSystem {

    public SelectRenderSystem(final int priority, final SpriteBatch batch) {
        super(Family.all(Selected.class, Position.class)
                    .get(), priority, batch);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Position position = Mapper.position.get(entity);
        final Selected selected = Mapper.selected.get(entity);

        final Sprite selection = selected.sprite;
        //TODO ogarnac sensowniej dla rzeczy ktore sa zaznaczalne, ale nie pokazuja gwiazdki (jak dziura)
        if (selection == null) return;
        final float radius = Selected.SIZE / 2;
        selection.setPosition(position.x - radius, position.y - radius);
        selection.setRotation(selection.getRotation() + 5f);
        selection.draw(getBatch());
    }
}
