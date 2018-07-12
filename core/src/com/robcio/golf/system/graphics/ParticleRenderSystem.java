package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;

public class ParticleRenderSystem extends BatchIteratingSystem {

    public ParticleRenderSystem(final int priority, final SpriteBatch batch) {
        super(Family.all(Particle.class, Position.class)
                    .get(), priority, batch);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Position position = Mapper.position.get(entity);
        final ParticleEffect particleEffect = Mapper.particle.get(entity).particleEffect;

        if(particleEffect.isComplete())particleEffect.start();

        particleEffect.setPosition(position.x, position.y);
        particleEffect.draw(getBatch(), deltaTime);
        Log.i("Ti");
    }
}
