package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import lombok.Getter;

@Getter
public class GameInputCatcher implements InputProcessor {

    private final MouseController mouseController;
    private final KeyboardController keyboardController;

    public GameInputCatcher(final Camera camera, final Engine engine) {
        this.mouseController = new MouseController(camera, engine);
        this.keyboardController = new KeyboardController();
    }

    public void update(final float deltaTime) {
        mouseController.update(deltaTime);
        keyboardController.update(deltaTime);
    }

    @Override
    public boolean keyDown(final int keycode) {
        return keyboardController.keyDown(keycode);
    }

    @Override
    public boolean keyUp(final int keycode) {
        return keyboardController.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(final char character) {
        return keyboardController.keyTyped(character);
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        return mouseController.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        return mouseController.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        return mouseController.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(final int screenX, final int screenY) {
        return mouseController.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(final int amount) {
        return mouseController.scrolled(amount);
    }
}
