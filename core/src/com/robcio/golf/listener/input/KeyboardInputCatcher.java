package com.robcio.golf.listener.input;

import com.robcio.golf.control.MouseMode;
import com.robcio.golf.utils.Command;

import java.util.HashMap;
import java.util.Map;

public class KeyboardInputCatcher {

    private final MouseModeController mouseModeController;
    //TODO w razie pojawienia sie wiekszej ilosci rzeczy mozna byc zrobic obiekt przechowujacy tylko mape
    private final Map<Integer, Command> keyMap;

    public KeyboardInputCatcher(final MouseModeController mouseModeController) {
        this.mouseModeController = mouseModeController;
        this.keyMap = new HashMap<>();
        setKeyMapForMouseModes();
    }

    private void setKeyMapForMouseModes() {
        for (final MouseMode mode: mouseModeController.getMouseModes()) {
            put(mode.getShortcutKey(), getMouseModeCommandFor(mode));
        }
    }

    private void put(final Integer shortcutKey, final Command mouseMode) {
        if (keyMap.containsKey(shortcutKey)) {
            throw new IllegalArgumentException(String.format("Key code %d already assigned", shortcutKey));
        }
        keyMap.put(shortcutKey, mouseMode);
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
