package com.robcio.golf.system.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.robcio.golf.component.util.CommandComponent;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.utils.Mapper;

//TODO znalezc zastosowanie dla tego bo narazie tylko dla myszkow sie przydaje
public class LateCommandExecuter extends IteratingSystem {

    public LateCommandExecuter(final int priority) {
        super(Family.all(CommandComponent.class)
                    .get(), priority);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        Mapper.commandComponent.get(entity).command.execute();
        entity.add(ToRemove.self());
    }

}
