package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;

public class ParticleLoader implements EntityListener {

    final public static Family family = Family.all(Particle.class)
                                              .get();

    @Override
    public void entityAdded(Entity entity) {
        final Particle particle = Mapper.particle.get(entity);
        particle.particleEffect = new ParticleEffect();
        particle.particleEffect.load(Gdx.files.internal(particle.id.getFilename()),
                                     Assets.getTextureAtlas());

    }

    @Override
    public void entityRemoved(Entity entity) {
        //TODO zwrocic particla do jakiegos poola czy cos
    }
}
