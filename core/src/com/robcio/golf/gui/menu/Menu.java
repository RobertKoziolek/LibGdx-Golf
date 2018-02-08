package com.robcio.golf.gui.menu;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.golf.MainClass;
import com.robcio.golf.enumeration.MapId;
import com.robcio.golf.enumeration.ScreenId;
import com.robcio.golf.gui.AbstractScreen;
import com.robcio.golf.gui.game.GameScreen;
import com.robcio.golf.utils.Assets;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;

public class Menu extends AbstractScreen {

    //TODO mozna wywalic do stagecontrollera jak w przypadku gamescreena
    //TODO jakies ruchome tlo z tylu, moze jako dekorator do ekranow?
    public Menu(final MainClass mainClass, final Camera camera, final GameScreen gameScreen) {
        super(camera);

        int position = HEIGHT / 2 - HEIGHT / 6;
        final int step = HEIGHT / 7;
        for (final MapId map : MapId.values()) {
            if (map == MapId.EMPTY) continue;
            final Button button = new TextButton(map.getName(), Assets.getSkin());
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    mainClass.setScreen(ScreenId.GAME);
                    gameScreen.setMap(map);
                }
            });
            button.setBounds(WIDTH / 2, position, WIDTH / 3, HEIGHT / 9);
            position += step;
            getStage().addActor(button);
        }
    }

    @Override
    public void show() {

    }
}
