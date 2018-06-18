package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.robcio.golf.component.structure.Timer;
import com.robcio.golf.utils.Mapper;

public class TimerSystem extends IteratingSystem {

    public TimerSystem(final int priority) {
        super(Family.all(Timer.class).get(), priority);
        setProcessing(true);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Timer timer = Mapper.timer.get(entity);
        if (timer.value <= 0f) {
            entity.add(timer.component);
        } else {
            timer.value -= deltaTime;
        }
    }
}
