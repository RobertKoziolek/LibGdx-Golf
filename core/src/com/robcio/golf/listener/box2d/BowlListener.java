package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.physics.InBowl;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

import java.util.Map;

@Getter
public class BowlListener implements BodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.BOWL;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;

    public void beginContact(final Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Body bowl = map.get(EntityFlags.BOWL.getId());
        final Entity ballEntity = (Entity) ball.getUserData();
        final Entity bowlEntity = (Entity) bowl.getUserData();

        final InBowl inBowl = new InBowl(bowl.getPosition(), Mapper.dimension.get(bowlEntity));
        final InBowl previousInBowl = Mapper.inBowl.get(ballEntity);
        if (previousInBowl != null) {
            inBowl.wasInAnother = previousInBowl.wasInAnother + 1;
        }

        ballEntity.add(inBowl);
    }

    @Override
    public void endContact(Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Entity entity = (Entity) ball.getUserData();
        final InBowl inBowl = Mapper.inBowl.get(entity);
        if (inBowl.wasInAnother != 0) {
            inBowl.wasInAnother--;
        } else {
            entity.remove(InBowl.class);
        }
    }
}
