package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.entity.Notification;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;
import lombok.Getter;

import java.util.Map;

@Getter
public class BumperListener implements BodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.BUMPER;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;

    private final Engine engine;

    public BumperListener(final Engine engine){
        this.engine = engine;
    }

    public void beginContact(final Map<Integer, Body> map) {
        //TODO rozwiazuje problem lepienia sie do tego, ale moze odbija 2x
        endContact(map);
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
        engine.addEntity(new Notification("A ball hit the bumper here"));
    }
}
