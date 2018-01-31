package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.robcio.golf.component.*;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.system.SelectionSystem;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class InputCatcher implements InputProcessor {

    private final OrthographicCamera camera;

    private final Engine engine;

    private final SelectionSystem selectionSystem;

    private boolean creating = false;

    public InputCatcher(OrthographicCamera camera, Engine engine) {
        this.camera = camera;
        this.engine = engine;
        this.selectionSystem = engine.getSystem(SelectionSystem.class);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        final Position unprojectedPosition = getUnprojectedPosition(screenX, screenY);
        if (creating) {
            engine.addEntity(new Ball(unprojectedPosition, Dimension.of(30)));
        } else {
            final ImmutableArray<Entity> entities = engine
                    .getEntitiesFor(Family.all(Position.class).exclude(Selected.class).get());
            for (final Entity entity : entities) {
                final Position position = Mapper.position.get(entity);
                if (Position.distance(unprojectedPosition, position) < 30f) {
                    setSelectionPoint(screenX, screenY);
                    entity.add(new Selected());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!creating) {
            final ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(Selected.class).get());
            for (final Entity entity : entities) {
                entity.remove(Selected.class);
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!creating) {
            setSelectionPoint(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private Position getUnprojectedPosition(final int screenX, final int screenY) {
        final Vector3 realCoords = camera.unproject(new Vector3(screenX, screenY, 0f));
        return Position.of(realCoords.x, realCoords.y);
    }

    public boolean changeBehaviour() {
        this.creating = !creating;
        selectionSystem.setProcessing(!creating);
        return creating;
    }

    private void setSelectionPoint(int screenX, int screenY) {
        final Position position = getUnprojectedPosition(screenX, screenY);
        position.x = position.x / Maths.PPM;
        position.y = position.y / Maths.PPM;
        Selected.position = position;
    }
}
