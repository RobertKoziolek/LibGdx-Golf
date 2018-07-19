package com.robcio.golf.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Color;
import com.robcio.golf.component.flag.Tether;
import com.robcio.golf.component.flag.Tetherable;
import com.robcio.golf.component.graphics.Tinted;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.entity.graphics.Lightning;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

public class LightningTetherSystem extends GroupedIteratingSystem {
    @Getter
    private final ComponentMapper groupMapper = Mapper.tetherable;
    @Getter
    private final ComponentMapper inGroupMapper = Mapper.tether;

    private float time;

    public LightningTetherSystem(final int priority) {
        super(Family.one(Tether.class, Tetherable.class)
                    .get(), priority);
    }

    @Override
    public void update(float deltaTime) {
        if ((time += deltaTime) > 0.03f) {
            super.update(deltaTime);
            time = 0f;
        }
    }

    @Override
    protected void processEntity(final Entity entity, final InGroup inGroup) {
        addLightning(entity, (Tether) inGroup);
    }

    private void addLightning(Entity entity, Tether tether) {
        Color color = Color.WHITE;
        final Tinted tinted = Mapper.tinted.get(entity);
        if (tinted != null) {
            color = tinted.color;
        }

        getEngine().addEntity(Lightning.of(tether.position1, tether.position2, color));
    }
}
