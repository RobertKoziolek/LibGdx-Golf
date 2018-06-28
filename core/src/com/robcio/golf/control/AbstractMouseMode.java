package com.robcio.golf.control;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.flag.Selected;
import com.robcio.golf.component.flag.ToRemove;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.listener.input.PointerPosition;
import com.robcio.golf.utils.Mapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractMouseMode implements MouseMode {

    protected final Engine engine;

    protected final PointerPosition pointerPosition;

    protected boolean select(final Family family, final boolean selectOne) {
        final ImmutableArray<Entity> moveEntities = engine
                .getEntitiesFor(family);
        for (final Entity entity: moveEntities) {
            final Position position = Mapper.position.get(entity);
            if (Position.distance(pointerPosition.getUnprojectedPosition(), position) < 30f) {
                pointerPosition.updateSelectionPoint();
                entity.add(new Selected());
                if (selectOne) return true;
            }
        }
        return false;
    }

    protected void selectEverything() {
        final ImmutableArray<Entity> entities = engine
                .getEntitiesFor(Family.all(Selectable.class, Kickable.class).get());
        for (final Entity entity: entities) {
            entity.add(new Selected());
        }
    }

    protected void deselectEverything() {
        final ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(Selectable.class).get());
        for (final Entity entity: entities) {
            entity.add(new ToRemove(Selected.class));
        }
    }

    @Override
    public void changeSystemProcessing(final boolean processing) {
        final Class<? extends EntitySystem> systemClass = getSystemClass();
        if (systemClass != null) {
            final EntitySystem system = engine.getSystem(systemClass);
            system.setProcessing(processing);
        }
        if (processing) {
            before();
        } else {
            after();
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void before() {
    }

    @Override
    public void after() {
    }

    @Override
    public boolean touchDown() {
        return false;
    }

    @Override
    public boolean touchUp() {
        return false;
    }

    @Override
    public boolean touchDragged() {
        return false;
    }

    @Override
    public Class<? extends EntitySystem> getSystemClass() {
        return null;
    }

    @Override
    abstract public String getTooltip();
}
