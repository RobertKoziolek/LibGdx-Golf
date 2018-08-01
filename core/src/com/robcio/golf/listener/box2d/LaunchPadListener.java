package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.component.physics.ChangeFilter;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.EntityHolder;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

@Getter
public class LaunchPadListener extends EngineBodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.LAUNCHPAD};
    private final EntityFlags[] entityFlagsB = {EntityFlags.LAUNCHING_BALL};

    public LaunchPadListener(final Engine engine) {
        super(engine);
    }

    public void beginContact(final EntityHolder entityHolder) {
        final Entity launchpad = entityHolder.getA();
        Mapper.dispensing.get(launchpad).active = false;
    }

    @Override
    public void endContact(final EntityHolder entityHolder) {
        final Entity launchpad = entityHolder.getA();
        Mapper.dispensing.get(launchpad).active = true;
        //TODO rozwinac changeFilter tak by pilka poki na launchpadzie byla duchem praktycznie, moze dodac te sciane na duchy w razie co

        final Entity ball = entityHolder.getB();
        ball.flags = EntityFlags.BALL.getId();
        ball.add(ToRemove.of(Particle.class));
        ball.add(ChangeFilter.ofCategory(Bits.C.BALL));

    }
}
