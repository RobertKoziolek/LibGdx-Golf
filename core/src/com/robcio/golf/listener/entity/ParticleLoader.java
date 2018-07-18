package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;

//TODO zaczac prace w koncu nad blokowanymi mapami i przechodzeniem lvl po levelunn, jakis system punktow itd
public class ParticleLoader implements EntityListener {

    final public static Family family = Family.all(Particle.class, Dimension.class)
                                              .get();

    @Override
    public void entityAdded(Entity entity) {
        final Particle particle = Mapper.particle.get(entity);
        particle.particleEffect = new ParticleEffect();
        particle.particleEffect.load(Gdx.files.internal(particle.id.getFilename()),
                                     Assets.getTextureAtlas());

        final ParticleEmitter particleEmitter = particle.particleEffect.getEmitters()
                                                                       .get(0);
        final float lowerSide = Mapper.dimension.get(entity).getLower();
        particleEmitter.getSpawnHeight().setHigh(lowerSide);
        particleEmitter.getSpawnHeight().setLow(lowerSide);
        particleEmitter.getSpawnWidth().setHigh(lowerSide);
        particleEmitter.getSpawnWidth().setLow(lowerSide);
    }

    @Override
    public void entityRemoved(Entity entity) {
        //TODO zwrocic particla do jakiegos poola czy cos
    }
}
