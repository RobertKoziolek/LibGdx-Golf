package com.robcio.golf.gui.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.gui.Box2dScreen;
import com.robcio.golf.listener.input.GameInputCatcher;
import com.robcio.golf.map.MapReader;
import com.robcio.golf.world.BodyDestroyer;

public class GameScreen extends Box2dScreen {

    final private StageController stageController;
    final private GameInputCatcher gameInputCatcher;

    public GameScreen(final World world, final Engine engine, final BodyDestroyer bodyDestroyer, final Camera camera) {
        super(world, engine, bodyDestroyer, camera);
        gameInputCatcher = new GameInputCatcher(camera, engine);
        stageController = new StageController(camera, gameInputCatcher, engine);

        setStage(stageController);
    }

    @Override
    public void show() {

    }

    @Override
    public InputProcessor[] getInputs(){
        return new InputProcessor[]{stageController, gameInputCatcher};
    }
}
