package com.robcio.golf.listener.box2d.listener;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.physics.InBowl;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.box2d.holder.ContactInfoHolder;
import com.robcio.golf.listener.box2d.GroupedBodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

@Getter
public class BowlListener extends GroupedBodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.BOWL};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BALL, EntityFlags.BOX};
    private final ComponentMapper groupMapper = Mapper.inBowlable;

    @Override
    protected InGroup create(final ContactInfoHolder contactInfoHolder) {
        final Entity bowlEntity = contactInfoHolder.getA();
        final Body bowl = contactInfoHolder.getBodyA();
        return new InBowl(bowl.getPosition(), Mapper.dimension.get(bowlEntity), bowlEntity);
    }
}
