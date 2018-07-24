package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.flag.Tether;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.EntityHolder;
import com.robcio.golf.listener.GroupedBodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

@Getter
//TODO kiedy motionem chwyce pilke razem z rodem to wywala sie jakims bledem z box2d
public class RodListener extends GroupedBodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.ROD};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BALL, EntityFlags.BOX};
    private final ComponentMapper groupMapper = Mapper.tetherable;

    @Override
    protected InGroup create(final EntityHolder entityHolder) {

        final Entity rodEntity = entityHolder.getA();
        final Entity ballEntity = entityHolder.getB();
        return new Tether(Mapper.position.get(rodEntity), Mapper.position.get(ballEntity), rodEntity);
    }
}
