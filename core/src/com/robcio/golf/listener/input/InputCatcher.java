package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Impulse;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.system.AttractionSystem;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Maths;

public class InputCatcher implements InputProcessor {

    private final OrthographicCamera camera;

    private final Engine engine;

    private final AttractionSystem attractionSystem;

    private boolean creating = true;

    public InputCatcher(OrthographicCamera camera, Engine engine) {
        this.camera = camera;
        this.engine = engine;
        this.attractionSystem = engine.getSystem(AttractionSystem.class);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.C) {
            //TODO czemu nie usuwa wszystkich tylko czesc?
            removeBalls();
        }
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
        if (pointer > 0) {
            removeBalls();
        } else if (creating) {
            engine.addEntity(new Ball(getUnprojectedPosition(screenX, screenY), Dimension.of(15)));
        } else {
            attractionSystem.setProcessing(true);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            attractionSystem.setProcessing(false);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (creating && pointer == 0) {
            engine.addEntity(new Ball(getUnprojectedPosition(screenX, screenY), Dimension.of(15)));
        } else {
            final Position position = getUnprojectedPosition(screenX, screenY);
            attractionSystem.position.x = position.x/ Maths.PPM;
            attractionSystem.position.y = position.y/ Maths.PPM;

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

    private void removeBalls() {
        final ImmutableArray<Entity> entities = engine
                .getEntitiesFor(Family.all(Impulse.class, Box2dBody.class).get());
        for (Entity entity : entities) {
            engine.removeEntity(entity);
        }
    }

    int anInt = 0;

    public void changeBehaviour() {
        this.creating = !creating;
        attractionSystem.setProcessing(true);
        Log.i("c" + creating + ++anInt);
    }
}
