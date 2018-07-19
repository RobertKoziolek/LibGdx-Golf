package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Tether;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.GroupedBodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

import java.util.Map;

@Getter
public class RodListener extends GroupedBodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.ROD;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;
    private final ComponentMapper groupMapper = Mapper.tetherable;

    @Override
    protected InGroup create(final Map<Integer, Body> map) {
        final Entity rodEntity = getEntityA(map);
        final Entity ballEntity = getEntityB(map);
        return new Tether(Mapper.position.get(rodEntity), Mapper.position.get(ballEntity), rodEntity);
    }
}
