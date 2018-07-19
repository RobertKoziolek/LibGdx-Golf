package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.robcio.golf.component.flag.Tether;
import com.robcio.golf.component.graphics.Tinted;
import com.robcio.golf.entity.graphics.Lightning;
import com.robcio.golf.utils.Mapper;

public class LightningTetherSystem extends IteratingSystem {

    private float time;

    public LightningTetherSystem(final int priority) {
        super(Family.all(Tether.class)
                    .get(), priority);
    }

    @Override
    public void update(float deltaTime) {
        if ((time += deltaTime) > 0.03f) {
            super.update(deltaTime);
            time = 0f;
        }
    }

    //TODO jak inne rodzaje tethera to trzeba edzie z tego componentu wyciagac rodzaj i moze color
    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Tether tether = Mapper.tether.get(entity);

        Color color = Color.WHITE;
        final Tinted tinted = Mapper.tinted.get(entity);
        if (tinted != null) {
            color = tinted.color;
        }

        getEngine().addEntity(Lightning.of(tether.position1, tether.position2, color));
    }
}
