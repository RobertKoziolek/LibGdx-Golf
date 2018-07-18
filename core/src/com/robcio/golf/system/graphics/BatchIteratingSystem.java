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
    private boolean changed;

    public BatchIteratingSystem(final Family family, final int priority, final SpriteBatch batch) {
        super(family, priority);
        this.batch = batch;
    }

    @Override
    final public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        if (changed) {
            throw new IllegalStateException("Color was not reset after change");
        }
        batch.end();
    }

    final protected void setColor(final Color color) {
        changed=true;
        batch.setColor(color);
    }

    final protected void setAlpha(final float a) {
        changed=true;
        final Color color = batch.getColor();
        batch.setColor(color.r, color.g, color.b, a);
    }

    final protected void resetColor() {
        if (!changed) {
            throw new IllegalStateException("Trying to reset color when there was no change to it beforehand");
        }
        batch.setColor(Color.WHITE);
        changed = false;
    }
}
