package com.robcio.golf.listener.input;

import com.robcio.golf.control.MouseMode;
import com.robcio.golf.utils.Command;

import java.util.HashMap;
import java.util.Map;

public class KeyboardInputCatcher {

    private final MouseModeController mouseModeController;
    private final Map<Integer, Command> keyMap;

    public KeyboardInputCatcher(final MouseModeController mouseModeController) {
        this.mouseModeController = mouseModeController;
        this.keyMap = getKeyMap();
    }

    private Map<Integer, Command> getKeyMap() {
        final Map<Integer, Command> map = new HashMap<>();
        for (final MouseMode mode: mouseModeController.getMouseModes()) {
            map.put(mode.getShortcutKey(), getMouseModeCommandFor(mode));
        }
        return map;
    }

    private Command getMouseModeCommandFor(final MouseMode mouseMode) {
        return new Command() {
            @Override
            public void execute() {
                mouseModeController.changeMouseModeTo(mouseMode);
            }
        };
    }

    public void update(final float deltaTime) {
    }

    public boolean keyDown(final int keycode) {
        if (!keyMap.containsKey(keycode)) return false;
        keyMap.get(keycode)
              .execute();
        return true;
    }

    public boolean keyUp(final int keycode) {
        //Nothing to do here
        return false;
    }

    public boolean keyTyped(final char character) {
        //Nothing to do here
        return false;
    }

    public void doFirstForNewMap() {
        //Nothing to do here
    }
}