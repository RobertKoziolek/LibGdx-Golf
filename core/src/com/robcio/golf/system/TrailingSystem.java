package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.flag.Trailing;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Impulse;
import com.robcio.golf.entity.recipe.EntityFactory;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class TrailingSystem extends IteratingSystem {

    public TrailingSystem(final int priority) {
        super(Family.all(Trailing.class, Box2dBody.class).get(), priority);
        setProcessing(true);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Trailing trailing = Mapper.trailing.get(entity);
        final Recipe recipe = trailing.recipe;
        final float triggerSpeed = trailing.triggerSpeed;

        final Box2dBody box2dBody = Mapper.box2dBody.get(entity);
        final Vector2 velocity = box2dBody.body.getLinearVelocity();


        if (velocity.len() > triggerSpeed) {
            if (trailing.counter++ > 10) {
                trailing.counter = 0;
                return;
            }
            final Entity newEntity = EntityFactory.createFrom(recipe);
            newEntity.add(new Impulse(velocity.rotate(130+ Maths.nextInt(100))));
            getEngine().addEntity(newEntity);
        }


    }
}
