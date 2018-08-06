package com.robcio.golf.listener.box2d.listener;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.flag.Tether;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.box2d.holder.ContactInfoHolder;
import com.robcio.golf.listener.box2d.GroupedBodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

@Getter
//TODO kiedy motionem chwyce pilke razem z rodem to wywala sie jakims bledem z box2d
public class RodListener extends GroupedBodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.ROD};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BALL, EntityFlags.BOX};
    private final ComponentMapper groupMapper = Mapper.tetherable;

    @Override
    protected InGroup create(final ContactInfoHolder contactInfoHolder) {

        final Entity rodEntity = contactInfoHolder.getA();
        final Entity ballEntity = contactInfoHolder.getB();
        return new Tether(Mapper.position.get(rodEntity), Mapper.position.get(ballEntity), rodEntity);
    }
}
