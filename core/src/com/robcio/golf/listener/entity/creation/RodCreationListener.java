package com.robcio.golf.listener.entity.creation;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.flag.Rod;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.light.LightEntity;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RodCreationListener implements EntityListener {

    final public static Family family = Family.all(Rod.class)
                                              .get();

    final private Engine engine;

    @Override
    public void entityAdded(final Entity entity) {
        final Position position = Mapper.position.get(entity);
        final Dimension dimension = Mapper.dimension.get(entity);
        final Force force = Force.of(dimension.getLower() / Maths.PPM);
        engine.addEntity(new LightEntity(position, force));
    }

    @Override
    public void entityRemoved(final Entity entity) {
        //nothing to do here
    }
}
