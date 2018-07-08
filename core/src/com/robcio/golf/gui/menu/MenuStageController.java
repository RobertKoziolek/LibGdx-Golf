package com.robcio.golf.gui.menu;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.golf.MainClass;
import com.robcio.golf.enumeration.MapId;
import com.robcio.golf.enumeration.ScreenId;
import com.robcio.golf.gui.GuiAssembler;
import com.robcio.golf.gui.game.GameScreen;
import com.robcio.golf.utils.Command;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;

public class MenuStageController extends Stage {

    public MenuStageController(final MainClass mainClass, final Camera camera, final GameScreen gameScreen) {
        super(new FillViewport(WIDTH, HEIGHT, camera));

        int positionY = HEIGHT / 3 - HEIGHT / 6;
        final int step = HEIGHT / 7;
        for (final MapId map: MapId.values()) {
            if (map == MapId.EMPTY) continue;
            final Button button = GuiAssembler.textButtonOf(map.getName())
                                              .withSize(WIDTH / 3, HEIGHT / 9)
                                              .withPosition(WIDTH / 2, positionY)
                                              .withCommand(getMapChangeCommand(mainClass, gameScreen, map))
                                              .assemble();
            positionY += step;
            addActor(button);
        }
    }

    private Command getMapChangeCommand(final MainClass mainClass, final GameScreen gameScreen, final MapId map) {
        return new Command() {
            @Override
            public void execute() {
                mainClass.setScreen(ScreenId.GAME);
                gameScreen.setMap(map);
            }
        };
    }
}
