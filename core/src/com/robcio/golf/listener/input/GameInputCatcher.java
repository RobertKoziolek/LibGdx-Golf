package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.robcio.golf.control.MouseMode;
import com.robcio.golf.registrar.MouseModeRegistrar;
import com.robcio.golf.utils.Log;
import lombok.Getter;

public class GameInputCatcher implements InputProcessor {

    private final Camera camera;
    private final Engine engine;
    @Getter
    private MouseMode currentMouseMode;
    private final MouseModeRegistrar mouseModeRegistrar;
    private final PointerPosition pointerPosition;

    public GameInputCatcher(final Camera camera, final Engine engine) {
        this.camera = camera;
        this.engine = engine;
        pointerPosition = new PointerPosition(camera);
        mouseModeRegistrar = new MouseModeRegistrar(engine, pointerPosition);
        currentMouseMode = mouseModeRegistrar.getMouseModes().get(0);
        currentMouseMode.changeSystemProcessing(true);
    }

    public String changeMouseMode() {
        currentMouseMode.changeSystemProcessing(false);
        currentMouseMode = mouseModeRegistrar.next(currentMouseMode);
        currentMouseMode.changeSystemProcessing(true);
        Log.i("Mouse mode", currentMouseMode.getTooltip());
        return currentMouseMode.getTooltip();
    }

    private void updatePointerPosition(int screenX, int screenY) {
        pointerPosition.setX(screenX);
        pointerPosition.setY(screenY);
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
        updatePointerPosition(screenX, screenY);
        return currentMouseMode.touchDown();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        updatePointerPosition(screenX, screenY);
        return currentMouseMode.touchUp();
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        updatePointerPosition(screenX, screenY);
        return currentMouseMode.touchDragged();
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
