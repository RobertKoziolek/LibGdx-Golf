package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import lombok.Getter;

import java.util.Observable;

@Getter
public class GameInputCatcher implements InputProcessor {

    private final MouseInputCatcher mouseInputCatcher;
    private final KeyboardInputCatcher keyboardInputCatcher;

    public GameInputCatcher(final Camera camera, final Engine engine) {
        final PointerPosition pointerPosition = new PointerPosition(camera);
        final MouseModeController mouseModeController = new MouseModeController(engine, pointerPosition);
        this.mouseInputCatcher = new MouseInputCatcher(pointerPosition, mouseModeController);
        this.keyboardInputCatcher = new KeyboardInputCatcher(mouseModeController, engine);
    }

    public void update(final float deltaTime) {
        mouseInputCatcher.update(deltaTime);
        keyboardInputCatcher.update(deltaTime);
    }

    public void doFirstForNewMap() {
        mouseInputCatcher.doFirstForNewMap();
        keyboardInputCatcher.doFirstForNewMap();
    }

    //TODO byc moze to powinno byc magicznie wyciagniete bardzo daleko poza to
    public String getCurrentMouseModeTooltip() {
        return mouseInputCatcher.getMouseModeController()
                                .getCurrent()
                                .getTooltip();
    }

    //TODO przy wiekszej ilosci takie rozwiazywanie tego bedzie przezeralo sie przez caly kod
    public Observable getMouseModeButtonObservable() {
        return mouseInputCatcher.getMouseModeController();
    }

    @Override
    public boolean keyDown(final int keycode) {
        return keyboardInputCatcher.keyDown(keycode);
    }

    @Override
    public boolean keyUp(final int keycode) {
        return keyboardInputCatcher.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(final char character) {
        return keyboardInputCatcher.keyTyped(character);
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        return mouseInputCatcher.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        return mouseInputCatcher.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        return mouseInputCatcher.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(final int screenX, final int screenY) {
        return mouseInputCatcher.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(final int amount) {
        return mouseInputCatcher.scrolled(amount);
    }
}
