package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import lombok.Getter;

import java.util.Map;

@Getter
public class BoxFireListener implements BodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.BALL};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BOX};

    public BoxFireListener() {
    }

    public void beginContact(final Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Body box = map.get(EntityFlags.BOX.getId());
        final Entity ballEntity = (Entity) ball.getUserData();
        final Entity boxEntity = (Entity) box.getUserData();
        //TODO ogarnac inaczej oznaczanie particlow kiedy bedzie ich wiecej
        //TODO firespreader powinien pracowac na samych componentach, nie na konkretnych typach obiektu
        if (ballEntity.getComponent(Particle.class) != null && boxEntity.getComponent(Particle.class) == null) {
            boxEntity.add(Particle.onFire());
        }
    }

    @Override
    public void endContact(Map<Integer, Body> map) {
        //nothing to do here
    }
}
