package com.robcio.golf.gui.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.gui.Box2dScreen;
import com.robcio.golf.listener.input.GameInputCatcher;
import com.robcio.golf.utils.Command;
import com.robcio.golf.world.BodyDestroyer;

public class GameScreen extends Box2dScreen {

    final private GameStageController stageController;
    final private GameInputCatcher gameInputCatcher;

    public GameScreen(final Command menuCallback,
                      final World world,
                      final Engine engine,
                      final GameInputCatcher gameInputCatcher,
                      final BodyDestroyer bodyDestroyer,
                      final Camera camera) {
        super(world, bodyDestroyer);
        this.gameInputCatcher = gameInputCatcher;
        this.stageController = new GameStageController(menuCallback, camera, gameInputCatcher, engine);

        setStage(stageController);
    }

    @Override
    public void show() {
    }

    @Override
    public InputProcessor[] getInputs() {
        return new InputProcessor[]{stageController, gameInputCatcher};
    }
}
