package com.robcio.golf.gui.menu;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.golf.MainClass;
import com.robcio.golf.gui.AbstractScreen;
import com.robcio.golf.gui.game.GameScreen;

public class Menu extends AbstractScreen {

    public Menu(final MainClass mainClass, final Camera camera, final GameScreen gameScreen) {
        super();
        final MenuStageController stageController = new MenuStageController(mainClass, camera, gameScreen);

        setStage(stageController);
    }

    @Override
    public void show() {

    }
}
