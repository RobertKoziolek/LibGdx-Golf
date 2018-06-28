package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.flag.Dispensing;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.ToRemove;
import com.robcio.golf.component.structure.*;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class DispensingSystem extends IteratingSystem {

    public DispensingSystem(final int priority) {
        super(Family.all(Dispensing.class).get(), priority);
        setProcessing(true);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Dispensing dispensing = Mapper.dispensing.get(entity);

        final Recipe recipe = dispensing.recipe;

        final Entity newEntity = Ball.of(recipe);

        final Force force = Mapper.force.get(entity);
        if (force != null) {
            newEntity.add(new Impulse(new Vector2(force.value, force.value).rotate(Maths.nextFloat() * 720)));
        }
        newEntity.add(Renderable.of(TextureId.BUBBLE, 20));
        newEntity.add(Timer.of(0.3f, new ToRemove()));

        getEngine().addEntity(newEntity);

    }
}
