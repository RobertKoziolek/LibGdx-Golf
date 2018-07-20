package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import com.robcio.golf.listener.EntityHolder;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

//TODO ogarnac inaczej oznaczanie particlow kiedy bedzie ich wiecej
//TODO firespreader powinien pracowac na samych componentach, nie na konkretnych typach obiektu
@Getter
public class FireSpreaderListener implements BodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.BALL, EntityFlags.BOX};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BOX};

    public void beginContact(final EntityHolder entityHolder) {
        final Entity entityA = entityHolder.getA();
        final Entity entityB = entityHolder.getB();

        if (Mapper.particle.get(entityA) != null) {
            if (Mapper.particle.get(entityB) == null) {
                entityB.add(Particle.onFire());
            }
        } else if (Mapper.particle.get(entityB) != null) {
            if (Mapper.particle.get(entityA) == null) {
                entityA.add(Particle.onFire());
            }
        }
    }

    @Override
    public void endContact(final EntityHolder entityHolder) {
        //nothing to do here
    }
}
