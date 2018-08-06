package com.robcio.golf.component.particle;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.robcio.golf.enumeration.ParticleId;

public class Particle implements Component {

    public final ParticleId id;
    public ParticleEffect particleEffect;

    private Particle(final ParticleId id) {
        this.id = id;
    }

    public static Particle onFire() {
        return new Particle(ParticleId.FIRE);
    }

    public static Particle onWater() {
        return new Particle(ParticleId.WATER);
    }

    public static Particle of(final ParticleId particleId) {
        return new Particle(particleId);
    }
}
