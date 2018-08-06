package com.robcio.golf.listener.box2d.listener;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.ParticleId;
import com.robcio.golf.listener.box2d.holder.ContactInfoHolder;
import com.robcio.golf.listener.box2d.BodyListener;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

//TODO moze dodac sensor dla obiektow z ogniem, zeby rozprzestzenialo sie ciut z dystansu
@Getter
public class FireSpreaderListener implements BodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.BALL, EntityFlags.BOX};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BOX};

    public void beginContact(final ContactInfoHolder contactInfoHolder) {
        final Entity entityA = contactInfoHolder.getA();
        final Entity entityB = contactInfoHolder.getB();

        //TODO zobaczyc kiedys czy tego nie da sie zrobic bez takiego tempego sprawdzania ktory obiekt ma particla
        if (Mapper.particle.get(entityA) != null) {
            if (Mapper.particle.get(entityA).id == ParticleId.FIRE) {
                if (Mapper.particle.get(entityB) == null) {
                    entityB.add(Particle.onFire());
                }
            }
        } else if (Mapper.particle.get(entityB) != null) {
            if (Mapper.particle.get(entityB).id == ParticleId.FIRE) {
                if (Mapper.particle.get(entityA) == null) {
                    entityA.add(Particle.onFire());
                }
            }
        }
    }

    @Override
    public void endContact(final ContactInfoHolder contactInfoHolder) {
        //nothing to do here
    }
}
