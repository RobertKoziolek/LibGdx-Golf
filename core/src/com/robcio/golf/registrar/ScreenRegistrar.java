package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.MainClass;
import com.robcio.golf.enumeration.ScreenId;
import com.robcio.golf.gui.AbstractScreen;
import com.robcio.golf.gui.game.GameScreen;
import com.robcio.golf.gui.menu.Menu;
import com.robcio.golf.utils.Command;
import com.robcio.golf.world.BodyDestroyer;

import java.util.HashMap;
import java.util.Map;

public class ScreenRegistrar {

    private final Map<ScreenId, AbstractScreen> map;
    private AbstractScreen current;

    public ScreenRegistrar(final MainClass mainClass,
                           final Command menuCallback,
                           final World world,
                           final Engine engine,
                           final BodyDestroyer bodyDestroyer,
                           final Camera camera) {
        map = new HashMap<>();

        final GameScreen gameScreen = new GameScreen(menuCallback, world, engine, bodyDestroyer, camera);
        map.put(ScreenId.GAME, gameScreen);
        map.put(ScreenId.MENU, new Menu(mainClass, camera, gameScreen));
    }

    public AbstractScreen get(final ScreenId screenId) {
        if (!map.containsKey(screenId))
            throw new IllegalArgumentException("ScreenId " + screenId + " not implemented yet");
        current = map.get(screenId);
        return current;
    }

    public AbstractScreen getCurrent() {
        if (current == null) throw new IllegalStateException("Current screen cannot be null");
        return current;
    }

    public void dispose() {
        for (final AbstractScreen screen : map.values()){
            screen.dispose();
        }
    }
}