package com.robcio.golf.listener;

import com.badlogic.ashley.core.ComponentMapper;
import com.robcio.golf.component.util.Group;
import com.robcio.golf.component.util.InGroup;

public abstract class GroupedBodyListener implements BodyListener {

    protected abstract ComponentMapper<? extends Group> getGroupMapper();

    protected abstract InGroup create(final EntityHolder entityHolder);

    @Override
    public void beginContact(final EntityHolder entityHolder) {
        final Group group = getGroupMapper().get(entityHolder.getB());
        if (group != null) {
            group.set.add(create(entityHolder));
        }
    }

    @Override
    public void endContact(final EntityHolder entityHolder) {
        final Group<? extends InGroup> group = getGroupMapper().get(entityHolder.getB());
        if (group != null) {
            for (final InGroup inGroup: group.set) {
                if (inGroup.groupedBy == entityHolder.getA()) {
                    group.set.remove(inGroup);
                    break;
                }
            }
        }
    }
}
