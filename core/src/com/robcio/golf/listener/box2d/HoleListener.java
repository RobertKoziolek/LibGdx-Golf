package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class HoleListener implements BodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.HOLE;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;

    final private Engine engine;

    public void beginContact(final Map<Integer, Body> map) {
        final Entity ballEntity = (Entity) map.get(EntityFlags.BALL.getId()).getUserData();
        engine.removeEntity(ballEntity);
    }

    @Override
    public void endContact(Map<Integer, Body> map) {
        //nothing to do here
    }
}
