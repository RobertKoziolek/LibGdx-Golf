package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Tether;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

import java.util.Map;

@Getter
public class RodListener implements BodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.ROD;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;

    public void beginContact(final Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Body rod = map.get(EntityFlags.ROD.getId());
        final Entity ballEntity = (Entity) ball.getUserData();
        final Entity rodEntity = (Entity) rod.getUserData();
        ballEntity.add(new Tether(Mapper.position.get(rodEntity), Mapper.position.get(ballEntity)));
    }

    @Override
    public void endContact(Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Entity ballEntity = (Entity) ball.getUserData();
        ballEntity.remove(Tether.class);
    }
}
