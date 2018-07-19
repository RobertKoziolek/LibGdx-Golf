package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.physics.OnSlope;
import com.robcio.golf.component.structure.Direction;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.GroupedBodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

import java.util.Map;

@Getter
public class SlopeListener extends GroupedBodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.SLOPE;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;
    private final ComponentMapper groupMapper = Mapper.onSlopable;

    @Override
    protected InGroup create(Map<Integer, Body> map) {
        final Entity slopeEntity = getEntityA(map);

        final Direction direction = Mapper.direction.get(slopeEntity);
        final Force force = Mapper.force.get(slopeEntity);

        return new OnSlope(direction, force,slopeEntity);
    }
}
