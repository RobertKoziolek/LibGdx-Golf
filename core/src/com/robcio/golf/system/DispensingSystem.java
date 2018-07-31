package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Impulse;
import com.robcio.golf.component.util.Dispensing;
import com.robcio.golf.entity.recipe.EntityFactory;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class DispensingSystem extends IteratingSystem {

    public DispensingSystem(final int priority) {
        super(Family.all(Dispensing.class)
                    .get(), priority);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Dispensing dispensing = Mapper.dispensing.get(entity);
        if (!dispensing.active) {
            return;
        }

        final Recipe recipe = dispensing.recipe;

        final Entity newEntity = EntityFactory.createFrom(recipe);

        final Force force = Mapper.force.get(entity);
        if (force != null) {
            newEntity.add(new Impulse(new Vector2(force.value, 0f).rotate(Maths.nextFloat() * 720)));
        }
        newEntity.add(Particle.onFire());
        getEngine().addEntity(newEntity);
        dispensing.active = false;
    }
}
