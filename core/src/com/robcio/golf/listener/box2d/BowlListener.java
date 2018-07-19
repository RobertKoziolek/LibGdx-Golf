package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.physics.InBowl;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.GroupedBodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

import java.util.Map;

@Getter
public class BowlListener extends GroupedBodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.BOWL;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;
    private final ComponentMapper groupMapper = Mapper.inBowlable;

    @Override
    protected InGroup create(Map<Integer, Body> map) {
        final Entity bowlEntity = getEntityA(map);
        final Body bowl = getBodyA(map);
        return new InBowl(bowl.getPosition(), Mapper.dimension.get(bowlEntity), bowlEntity);
    }
}
