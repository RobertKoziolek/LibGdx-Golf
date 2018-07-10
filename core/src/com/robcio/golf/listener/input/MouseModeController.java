package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.control.MouseMode;
import com.robcio.golf.registrar.MouseModeRegistrar;
import com.robcio.golf.utils.Command;
import com.robcio.golf.utils.Log;
import lombok.Getter;

import java.util.List;
import java.util.Observable;

public class MouseModeController extends Observable {

    @Getter
    private MouseMode current;
    private final MouseModeRegistrar mouseModeRegistrar;

    public MouseModeController(final Engine engine, final PointerPosition pointerPosition) {
        this.mouseModeRegistrar = new MouseModeRegistrar(engine, pointerPosition);
        this.current = mouseModeRegistrar.getMouseModes()
                                         .get(0);
        this.current.changeSystemProcessing(true);
    }

    public void update(final float deltaTime) {
        current.update(deltaTime);
    }

    public void changeMouseModeTo(final MouseMode mouseMode) {
        current.changeSystemProcessing(false);
        current = mouseMode;
        mouseModeChanged();
    }

    public void changeMouseModeNext() {
        current.changeSystemProcessing(false);
        current = mouseModeRegistrar.next(current);
        mouseModeChanged();
    }

    public void changeMouseModePrevious() {
        current.changeSystemProcessing(false);
        current = mouseModeRegistrar.previous(current);
        mouseModeChanged();
    }

    private void mouseModeChanged() {
        current.changeSystemProcessing(true);
        Log.i("Mouse mode", current.getTooltip());
        setChanged();
        notifyObservers(current.getTooltip());
    }

    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        return current.touchDown();
    }

    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        return current.touchUp();
    }

    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        return current.touchDragged();
    }

    public boolean mouseMoved(final int screenX, final int screenY) {
        return false;
    }

    public Command getChangeMouseModeCommand() {
        return new Command() {
            @Override
            public void execute() {
                changeMouseModeNext();
            }
        };
    }

    public void doFirstForNewMap() {
        current.before();
    }

    public List<MouseMode> getMouseModes() {
        return mouseModeRegistrar.getMouseModes();
    }
}
