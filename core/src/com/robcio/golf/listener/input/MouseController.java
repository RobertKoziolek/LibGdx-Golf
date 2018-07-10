package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Camera;
import com.robcio.golf.control.MouseMode;
import com.robcio.golf.registrar.MouseModeRegistrar;
import com.robcio.golf.utils.Command;
import com.robcio.golf.utils.Log;
import lombok.Getter;

import java.util.Observable;

public class MouseController extends Observable {

    @Getter
    private MouseMode currentMouseMode;
    private final MouseModeRegistrar mouseModeRegistrar;
    private final PointerPosition pointerPosition;

    public MouseController(final Camera camera, final Engine engine) {
        pointerPosition = new PointerPosition(camera);
        mouseModeRegistrar = new MouseModeRegistrar(engine, pointerPosition);
        currentMouseMode = mouseModeRegistrar.getMouseModes()
                                             .get(0);
        currentMouseMode.changeSystemProcessing(true);
    }

    public void update(final float deltaTime) {
        currentMouseMode.update(deltaTime);
    }

    public void changeMouseModeNext() {
        currentMouseMode.changeSystemProcessing(false);
        currentMouseMode = mouseModeRegistrar.next(currentMouseMode);
        mouseModeChanged();
    }

    public void changeMouseModePrevious() {
        currentMouseMode.changeSystemProcessing(false);
        currentMouseMode = mouseModeRegistrar.previous(currentMouseMode);
        mouseModeChanged();
    }

    private void mouseModeChanged() {
        currentMouseMode.changeSystemProcessing(true);
        Log.i("Mouse mode", currentMouseMode.getTooltip());
        setChanged();
        notifyObservers(currentMouseMode.getTooltip());
    }

    private void updatePointerPosition(final int screenX, final int screenY) {
        pointerPosition.setX(screenX);
        pointerPosition.setY(screenY);
    }

    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        updatePointerPosition(screenX, screenY);
        return currentMouseMode.touchDown();
    }

    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        updatePointerPosition(screenX, screenY);
        return currentMouseMode.touchUp();
    }

    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        updatePointerPosition(screenX, screenY);
        return currentMouseMode.touchDragged();
    }

    public boolean mouseMoved(final int screenX, final int screenY) {
        return false;
    }

    public boolean scrolled(final int amount) {
        if (amount < 0) {
            changeMouseModePrevious();
        } else {
            changeMouseModeNext();
        }
        return true;
    }

    public Command getChangeMouseModeCommand() {
        return new Command() {
            @Override
            public void execute() {
                changeMouseModeNext();
            }
        };
    }
}
