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

        final Sprite selectionSprite = selected.sprite;
        //TODO dziura, ta mala, pokazuje sprita selected na diurze, pewnie przez to ze pozycja jest jedna a nie ma synchro dla box2d
        final float radius = Selected.SIZE / 2;
        selectionSprite.setPosition(position.x - radius, position.y - radius);
        selectionSprite.setRotation(selectionSprite.getRotation() + 5f);
        selectionSprite.draw(getBatch());
    }
}
