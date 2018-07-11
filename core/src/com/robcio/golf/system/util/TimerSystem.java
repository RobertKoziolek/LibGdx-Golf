package com.robcio.golf.system.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.robcio.golf.component.util.RepeatingTimer;
import com.robcio.golf.component.util.Timer;
import com.robcio.golf.utils.Mapper;

public class TimerSystem extends IteratingSystem {

    public TimerSystem(final int priority) {
        super(Family.one(Timer.class, RepeatingTimer.class).get(), priority);
        setProcessing(true);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        processTimer(entity, deltaTime);
        processRepeatingTimer(entity, deltaTime);
    }

    private void processTimer(final Entity entity, final float deltaTime) {
        final Timer timer = Mapper.timer.get(entity);
        if (timer == null || timer.done) return;
        if (timer.time <= 0f) {
            timer.done = true;
            entity.add(timer.component);
        } else {
            timer.time -= deltaTime;
        }
    }

    private void processRepeatingTimer(final Entity entity, final float deltaTime) {
        final RepeatingTimer repeatingTimer = Mapper.repeatingTimer.get(entity);
        if (repeatingTimer == null) return;
        if (repeatingTimer.time <= 0f) {
            repeatingTimer.reset();
            entity.add(repeatingTimer.component);
        } else {
            repeatingTimer.time -= deltaTime;
        }
    }
}
