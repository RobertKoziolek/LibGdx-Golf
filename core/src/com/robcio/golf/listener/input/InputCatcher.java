package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.robcio.golf.component.*;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.system.AttractionSystem;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
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
        if (creating) {
            engine.addEntity(new Ball(getUnprojectedPosition(screenX, screenY), Dimension.of(15)));
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (creating && pointer == 0) {
            engine.addEntity(new Ball(getUnprojectedPosition(screenX, screenY), Dimension.of(15)));
        } else {
            final Position position = getUnprojectedPosition(screenX, screenY);
            position.x = position.x / Maths.PPM;
            position.y = position.y / Maths.PPM;
            Attracted.position = position;
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

    public void changeBehaviour() {
        this.creating = !creating;
        attractionSystem.setProcessing(!creating);
    }
}
