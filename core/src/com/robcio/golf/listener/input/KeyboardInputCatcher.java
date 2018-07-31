package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.control.MouseMode;
import com.robcio.golf.system.graphics.*;
import com.robcio.golf.utils.Command;
import com.robcio.golf.utils.Log;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;
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
        setSystemKeys();
    }

    private void setSystemKeys() {
        setSystemKey(F1, RenderSystem.class, "Object rendering");
        setSystemKey(F2, ParticleRenderSystem.class, "Particle rendering");
        setSystemKey(F3, LightRenderSystem.class, "Light rendering");
        setSystemKey(F4, MapRenderSystem.class, "Map rendering");
        setSystemKey(F5, NotificationRenderSystem.class, "Notification rendering");
        setSystemKey(F6, LineRenderSystem.class, "Line rendering");
        setSystemKey(F7, LightningRenderSystem.class, "Lightning rendering");
        setSystemKey(F8, SelectRenderSystem.class, "Selection indicator rendering");
    }

    private void setSystemKey(final Integer key, final Class<? extends EntitySystem> systemClass, final String name) {
        put(key, new Command() {
            @Override
            public void execute() {
                final EntitySystem system = engine.getSystem(systemClass);
                system.setProcessing(!system.checkProcessing());
                Log.i(name + (system.checkProcessing() ? " On" : " Off"));
            }
        });
    }

    private void setDebugKey() {
        put(GRAVE, new Command() {
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

    private void put(final Integer shortcutKey, final Command mouseMode) {
        if (keyMap.containsKey(shortcutKey)) {
            throw new IllegalArgumentException(String.format("Key code %d already assigned", shortcutKey));
        }
        keyMap.put(shortcutKey, mouseMode);
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
