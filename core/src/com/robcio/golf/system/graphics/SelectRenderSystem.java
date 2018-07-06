package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.component.flag.Selected;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.utils.Mapper;

public class SelectRenderSystem extends IteratingSystem {

    final private SpriteBatch batch;

    public SelectRenderSystem(final int priority, final SpriteBatch batch) {
        super(Family.all(Selected.class, Position.class).get(), priority);
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Position position = Mapper.position.get(entity);
        final Selected selected = Mapper.selected.get(entity);

        final Sprite selection = selected.sprite;
        selection.setPosition(position.x - selection.getWidth() / 2, position.y - selection.getHeight() / 2);
        selection.setRotation(selection.getRotation() + 5f);
        selection.draw(batch);
    }
}
