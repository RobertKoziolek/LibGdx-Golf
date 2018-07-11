package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.entity.Notification;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import lombok.Getter;

import java.util.Map;

@Getter
public class HoleListener implements BodyListener {

    private final EntityFlags entityFlagsA = EntityFlags.HOLE;
    private final EntityFlags entityFlagsB = EntityFlags.BALL;

    private final Engine engine;

    public HoleListener(final Engine engine){
        this.engine = engine;
    }

    public void beginContact(final Map<Integer, Body> map) {
        final Entity ballEntity = (Entity) map.get(EntityFlags.BALL.getId()).getUserData();
        ballEntity.add(ToRemove.self());
        engine.addEntity(new Notification("Ball poted WOOOOAH!!"));
    }

    @Override
    public void endContact(Map<Integer, Body> map) {
        //nothing to do here
    }
}
