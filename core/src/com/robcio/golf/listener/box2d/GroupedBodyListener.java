package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.ComponentMapper;
import com.robcio.golf.component.util.Group;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.listener.box2d.holder.ContactInfoHolder;

public abstract class GroupedBodyListener implements BodyListener {

    protected abstract ComponentMapper<? extends Group> getGroupMapper();

    protected abstract InGroup create(final ContactInfoHolder contactInfoHolder);

    @Override
    public void beginContact(final ContactInfoHolder contactInfoHolder) {
        final Group group = getGroupMapper().get(contactInfoHolder.getB());

        group.set.add(create(contactInfoHolder));
        //TODO here lapanie custom exceptiona jesli w magicznych powodow nie stworzy componentu
    }

    @Override
    public void endContact(final ContactInfoHolder contactInfoHolder) {
        final Group<? extends InGroup> group = getGroupMapper().get(contactInfoHolder.getB());
        if (group != null) {
            for (final InGroup inGroup: group.set) {
                if (inGroup.groupedBy == contactInfoHolder.getA()) {
                    group.set.remove(inGroup);
                    break;
                }
            }
        }
    }
}
