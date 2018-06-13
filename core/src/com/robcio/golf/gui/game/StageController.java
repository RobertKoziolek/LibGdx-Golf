package com.robcio.golf.gui.game;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.golf.component.*;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.listener.input.GameInputCatcher;
import com.robcio.golf.utils.Assets;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;


public class StageController extends Stage {

    final private GameInputCatcher gameInputCatcher;
    final private Engine engine;

    StageController(final Camera camera,
                    final GameInputCatcher gameInputCatcher,
                    final Engine engine) {
        super(new FillViewport(WIDTH, HEIGHT, camera));
        this.gameInputCatcher = gameInputCatcher;
        this.engine = engine;
        setUp();
    }

    private void setUp() {
        final TextButton leftClickButton = addButton(gameInputCatcher.getCurrentMouseMode().getTooltip());
        leftClickButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final String mouseModeTooltip = gameInputCatcher.changeMouseMode();
                leftClickButton.setText(mouseModeTooltip);
            }
        });
        final ImmutableArray<Entity> entities = engine
                .getEntitiesFor(Family.all(Position.class, Dimension.class, Renderable.class, Box2dBody.class).get());
        final Button clearBallsButton = addButton("Clear balls");
        clearBallsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (final Entity entity : entities) {
                    if (entity instanceof Ball){
                        entity.add(new ToRemove());
                    }
                }
            }
        });
        final Table table = new Table(Assets.getSkin());
        table.add(leftClickButton);
        table.add(clearBallsButton).row();
        final ScrollPane debugPane = new ScrollPane(table, Assets.getSkin());
        debugPane.setSize(WIDTH / 3, HEIGHT / 10);
        debugPane.setScrollingDisabled(true, false);
        debugPane.setupFadeScrollBars(0f, 0f);
        debugPane.setupOverscroll(15, 10, 55);
        addActor(debugPane);
    }

    private TextButton addButton(final String text) {
        final int index = getActors().size;
        final TextButton button = new TextButton(text, Assets.getSkin());
        final float width = WIDTH / numberOfButtonsInRow();
        final float height = HEIGHT / 12;
        button.setSize(width, height);
        button.setPosition(width + width * ((index - 1) % numberOfButtonsInRow()),
                           HEIGHT - height);
        return button;
    }

    private int numberOfButtonsInRow() {
        return 3;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
