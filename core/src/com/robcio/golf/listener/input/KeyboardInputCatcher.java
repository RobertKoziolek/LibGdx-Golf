package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Input;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.control.MouseMode;
import com.robcio.golf.utils.Command;
import com.robcio.golf.utils.Log;

import java.util.HashMap;
import java.util.Map;

import static com.robcio.golf.MainClass.DEBUG_INFO;

public class KeyboardInputCatcher {

    private final MouseModeController mouseModeController;
    //TODO w razie pojawienia sie wiekszej ilosci rzeczy mozna byc zrobic obiekt przechowujacy tylko mape
    private final Map<Integer, Command> keyMap;
    private final Engine engine;

    public KeyboardInputCatcher(final MouseModeController mouseModeController, final Engine engine) {
        this.mouseModeController = mouseModeController;
        this.engine = engine;
        this.keyMap = new HashMap<>();
        setKeyMapForMouseModes();
        setDebugKey();
    }

    private void setDebugKey() {
        put(Input.Keys.GRAVE, new Command() {
            @Override
            public void execute() {
                if (engine.getEntities()
                          .contains(DEBUG_INFO, true)) {
                    Log.i("Debug Off");
                    DEBUG_INFO.add(ToRemove.self());
                } else {
                    Log.i("Debug On");
                    engine.addEntity(DEBUG_INFO);
                }
            }
        });
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
        //nothing to do here right now
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
