package com.robcio.golf.registrar;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.enumeration.ScreenId;
import com.robcio.golf.gui.AbstractScreen;
import com.robcio.golf.gui.game.GameScreen;
import com.robcio.golf.world.BodyDestroyer;

import java.util.HashMap;
import java.util.Map;

public class ScreenRegistrar {

    private final Map<ScreenId, AbstractScreen> map;
    private AbstractScreen current;

    public ScreenRegistrar(World world, Engine engine, BodyDestroyer bodyDestroyer, Camera camera) {
        map = new HashMap<>();
        map.put(ScreenId.GAME, new GameScreen(world, engine, bodyDestroyer, camera));
    }

    public AbstractScreen get(final ScreenId screenId) {
        if (!map.containsKey(screenId)) throw new IllegalArgumentException("ScreenId " + screenId + " not implemented yet");
        current = map.get(screenId);
        return current;
    }

    public AbstractScreen getCurrent() {
        if (current == null) throw new IllegalStateException("Current screen cannot be null");
        return current;
    }
}