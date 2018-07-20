package com.robcio.golf.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.robcio.golf.component.util.Group;
import com.robcio.golf.component.util.InGroup;

public abstract class GroupedIteratingSystem extends IteratingSystem {

    public GroupedIteratingSystem(final Family family, final int priority) {
        super(family, priority);
    }

    protected abstract ComponentMapper<? extends Group> getGroupMapper();

    protected abstract void processEntity(final Entity entity, final InGroup inGroup);

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Group<? extends InGroup> group = getGroupMapper().get(entity);
        if (group != null) {
            for (final InGroup inGroup: group.set) {
                processEntity(entity, inGroup);
            }
        }
    }
}
