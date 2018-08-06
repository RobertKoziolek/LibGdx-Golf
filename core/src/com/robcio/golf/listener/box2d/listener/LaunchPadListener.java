package com.robcio.golf.listener.box2d.listener;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.physics.ChangeFilter;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.box2d.holder.ContactInfoHolder;
import com.robcio.golf.listener.box2d.BodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

@Getter
public class LaunchPadListener implements BodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.LAUNCHPAD};
    private final EntityFlags[] entityFlagsB = {EntityFlags.LAUNCHING_BALL};

    public void beginContact(final ContactInfoHolder contactInfoHolder) {
        final Entity launchpad = contactInfoHolder.getA();
        Mapper.dispensing.get(launchpad).active = false;
    }

    @Override
    public void endContact(final ContactInfoHolder contactInfoHolder) {
        final Entity launchpad = contactInfoHolder.getA();
        Mapper.dispensing.get(launchpad).active = true;

        final Entity ball = contactInfoHolder.getB();
        ball.flags = EntityFlags.BALL.getId();
//        ball.add(ToRemove.of(Particle.class));
        ball.add(ChangeFilter.ofCategory(Bits.C.BALL));

    }
}
