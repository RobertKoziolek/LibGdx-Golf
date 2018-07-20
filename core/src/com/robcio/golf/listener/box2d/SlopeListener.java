package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.physics.OnSlope;
import com.robcio.golf.component.structure.Direction;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.EntityHolder;
import com.robcio.golf.listener.GroupedBodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

@Getter
public class SlopeListener extends GroupedBodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.SLOPE};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BALL, EntityFlags.BOX};
    private final ComponentMapper groupMapper = Mapper.onSlopable;

    @Override
    protected InGroup create(final EntityHolder entityHolder) {
        final Entity slopeEntity = entityHolder.getA();

        final Direction direction = Mapper.direction.get(slopeEntity);
        final Force force = Mapper.force.get(slopeEntity);

        return new OnSlope(direction, force,slopeEntity);
    }
}
