package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.Force;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

import java.util.Map;

@Getter
public class BumperListener implements BodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.BUMPER;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;

    public void beginContact(final Map<Integer, Body> map) {
        //nothing to do here
    }

    @Override
    public void endContact(Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Body bumper = map.get(EntityFlags.BUMPER.getId());
        final Entity entity = (Entity) bumper.getUserData();
        final Force force = Mapper.force.get(entity);

        Vector2 distance = new Vector2(0f, 0f);
        distance.add(ball.getPosition());
        distance.sub(bumper.getPosition());
        float finalDistance = distance.len();

        float vecSum = Math.abs(distance.x) + Math.abs(distance.y);
        distance = new Vector2(distance.x * (1 / vecSum) * force.value / finalDistance,
                               distance.y * (1 / vecSum) * force.value / finalDistance);
        ball.applyForceToCenter(distance, true);
    }
}
