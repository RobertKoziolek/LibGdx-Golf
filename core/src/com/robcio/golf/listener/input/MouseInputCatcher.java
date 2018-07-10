package com.robcio.golf.listener.input;

import com.robcio.golf.utils.Command;
import lombok.Getter;

public class MouseInputCatcher {

    private final PointerPosition pointerPosition;
    @Getter
    private final MouseModeController mouseModeController;

    public MouseInputCatcher(final PointerPosition pointerPosition, final MouseModeController mouseModeController) {
        this.pointerPosition = pointerPosition;
        this.mouseModeController = mouseModeController;
    }

    public void update(final float deltaTime) {
        mouseModeController.update(deltaTime);
    }

    private void updatePointerPosition(final int screenX, final int screenY) {
        pointerPosition.setX(screenX);
        pointerPosition.setY(screenY);
    }

    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        updatePointerPosition(screenX, screenY);
        return mouseModeController.touchDown(screenX, screenY, pointer, button);
    }

    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        updatePointerPosition(screenX, screenY);
        return mouseModeController.touchUp(screenX, screenY, pointer, button);
    }

    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        updatePointerPosition(screenX, screenY);
        return mouseModeController.touchDragged(screenX, screenY, pointer);
    }

    public boolean mouseMoved(final int screenX, final int screenY) {
        return mouseModeController.mouseMoved(screenX, screenY);
    }

    public boolean scrolled(final int amount) {
        if (amount < 0) {
            mouseModeController.changeMouseModePrevious();
        } else {
            mouseModeController.changeMouseModeNext();
        }
        return true;
    }

    public Command getChangeMouseModeCommand() {
        return new Command() {
            @Override
            public void execute() {
                mouseModeController.changeMouseModeNext();
            }
        };
    }

    public void doFirstForNewMap() {
        mouseModeController.doFirstForNewMap();
    }
}
