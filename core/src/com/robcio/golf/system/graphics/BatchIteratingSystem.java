package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.AccessLevel;
import lombok.Getter;

public abstract class BatchIteratingSystem extends IteratingSystem {

    @Getter(AccessLevel.PROTECTED)
    final private SpriteBatch batch;
    private Color originalColor;

    public BatchIteratingSystem(final Family family, final int priority, final SpriteBatch batch) {
        super(family, priority);
        this.batch = batch;
    }

    @Override
    final public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    final protected void setAlpha(final float a) {
        originalColor = batch.getColor();
        getBatch().setColor(originalColor.r, originalColor.g, originalColor.b, a);
    }

    final protected void resetAlpha() {
        if (originalColor == null) {
            throw new IllegalArgumentException("Trying to reset alpha when there was no change to it beforehand");
        }
        batch.setColor(originalColor);
        originalColor = null;
    }
}
