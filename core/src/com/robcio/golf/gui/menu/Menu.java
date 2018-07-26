package com.robcio.golf.gui.menu;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.golf.MainClass;
import com.robcio.golf.gui.AbstractScreen;

public class Menu extends AbstractScreen {

    public Menu(final MainClass mainClass, final Camera camera) {
        super();
        final MenuStageController stageController = new MenuStageController(mainClass, camera);

        setStage(stageController);
    }

    @Override
    public void show() {

    }
}
