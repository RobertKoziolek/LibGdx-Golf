package com.robcio.golf.listener.entity.creation;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.flag.HoleCenter;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.Bowl;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Mapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HoleCreationListener implements EntityListener {

    final public static Family family = Family.all(HoleCenter.class).get();

    final private Engine engine;

    @Override
    public void entityAdded(final Entity entity) {
        final Position position = Mapper.position.get(entity);
        final Dimension dimension = Mapper.dimension.get(entity);
        engine.addEntity(new Bowl(position, dimension, 0f, TextureId.HOLE));
    }

    @Override
    public void entityRemoved(final Entity entity) {
        //TODO przebadac czy nie wypada usuwac tego bowla z kilku linijek wyzej
        //TODO potrzebne do obadania przy tworzeniu usuwania holi
        //nothing to do here
    }
}
