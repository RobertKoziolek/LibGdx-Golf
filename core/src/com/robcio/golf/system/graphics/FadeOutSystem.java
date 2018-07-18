package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.robcio.golf.component.graphics.FadeOut;
import com.robcio.golf.component.graphics.Renderable;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.utils.Mapper;

public class FadeOutSystem extends IteratingSystem {

    public FadeOutSystem(final int priority) {
        super(Family.one(FadeOut.class)
                    .get(), priority);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final FadeOut fadeOut = Mapper.fadeOut.get(entity);
        if ((fadeOut.time -= deltaTime) < 0f) {
            fadeOut.time = 0f;
            entity.add(ToRemove.self());
        } else {
            fadeOut.alpha -= fadeOut.rate * deltaTime;
        }
        final Renderable renderable = Mapper.renderable.get(entity);
        if (renderable != null) {
            renderable.sprite.setAlpha(fadeOut.alpha);
        }
    }
}
