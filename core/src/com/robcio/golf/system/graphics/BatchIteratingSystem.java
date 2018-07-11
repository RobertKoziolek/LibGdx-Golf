package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.AccessLevel;
import lombok.Getter;

public abstract class BatchIteratingSystem extends IteratingSystem {

    @Getter(AccessLevel.PROTECTED)
    final private SpriteBatch batch;

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
}
