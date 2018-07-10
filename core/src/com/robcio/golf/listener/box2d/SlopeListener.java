package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.physics.OnSlope;
import com.robcio.golf.component.structure.Direction;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

import java.util.Map;

@Getter
public class SlopeListener implements BodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.SLOPE;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;

    public void beginContact(final Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Body bowl = map.get(EntityFlags.SLOPE.getId());
        final Entity ballEntity = (Entity) ball.getUserData();
        final Entity slopeEntity = (Entity) bowl.getUserData();

        final Direction direction = Mapper.direction.get(slopeEntity);
        final Force force = Mapper.force.get(slopeEntity);

        ballEntity.add(new OnSlope(direction, force));
    }

    @Override
    public void endContact(Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Entity entity = (Entity) ball.getUserData();
        entity.remove(OnSlope.class);
    }
}
