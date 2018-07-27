package com.robcio.golf.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.robcio.golf.component.physics.ChangeFilter;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.utils.Mapper;

public class BodyFilterChangingSystem extends IteratingSystem {

    public BodyFilterChangingSystem(final int priority) {
        super(Family.all(ChangeFilter.class, Box2dBody.class)
                    .get(), priority);
        setProcessing(true);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final ChangeFilter changeFilter = Mapper.changeFilter.get(entity);
        final Body body = Mapper.box2dBody.get(entity).body;

        for (final Fixture fixture: body.getFixtureList()) {
            final Filter filterData = fixture.getFilterData();
            filterData.categoryBits = changeFilter.categoryBits;
        }

        entity.add(ToRemove.of(ChangeFilter.class));
    }
}
