package com.robcio.golf.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.physics.OnSlopable;
import com.robcio.golf.component.physics.OnSlope;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

@Getter
public class SlopeSystem extends GroupedIteratingSystem {
    private final ComponentMapper groupMapper = Mapper.onSlopable;

    public SlopeSystem(final int priority) {
        super(Family.all(OnSlopable.class)
                    .get(), priority);
    }

    @Override
    protected void processEntity(final Entity entity, final InGroup inGroup) {
        processOnSlope(entity, (OnSlope) inGroup);
    }

    private void processOnSlope(final Entity entity, final OnSlope onSlope) {
        final Body ball = Mapper.box2dBody.get(entity).body;
        final Vector2 direction = onSlope.direction.value;
        final float force = onSlope.force.value;

        ball.applyForceToCenter(direction.scl(force), true);
    }
}
