package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.Force;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;
import lombok.Getter;

import java.util.Map;

//TODO jesli pilka uderzy z mala energia to sie przykleja i nie ma endContact ;c
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

        final Vector2 distance = Maths.getDistance(ball.getPosition(), bumper.getPosition());
        final float finalDistance = distance.len();

        ball.applyForceToCenter(distance.scl((1 / Maths.getVectorSum(distance)) * force.value / finalDistance), true);
    }
}
