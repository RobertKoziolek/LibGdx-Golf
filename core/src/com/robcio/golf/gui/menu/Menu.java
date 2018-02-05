package com.robcio.golf.gui.menu;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.golf.MainClass;
import com.robcio.golf.enumeration.ScreenId;
import com.robcio.golf.gui.AbstractScreen;
import com.robcio.golf.utils.Assets;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;

public class Menu extends AbstractScreen {

    public Menu(final MainClass mainClass, final Camera camera) {
        super(camera);

        final Button button = new TextButton("Play da game!!1!!", Assets.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainClass.setScreen(ScreenId.GAME);
            }
        });
        button.setBounds(WIDTH/2, HEIGHT/2, WIDTH/3, HEIGHT/9);

        getStage().addActor(button);

    }

    @Override
    public void show() {

    }
}
