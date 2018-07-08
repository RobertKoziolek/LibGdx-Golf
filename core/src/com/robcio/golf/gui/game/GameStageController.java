package com.robcio.golf.gui.game;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.ToRemove;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.gui.utils.ButtonListener;
import com.robcio.golf.listener.input.GameInputCatcher;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Command;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;


public class GameStageController extends Stage {

    final private GameInputCatcher gameInputCatcher;
    final private Engine engine;

    GameStageController(final Command menuCallback,
                        final Camera camera,
                        final GameInputCatcher gameInputCatcher,
                        final Engine engine) {
        super(new FillViewport(WIDTH, HEIGHT, camera));
        this.gameInputCatcher = gameInputCatcher;
        this.engine = engine;
        setUp(menuCallback);
    }

    @Override
    public void act(final float deltaTime) {
        super.act(deltaTime);
        gameInputCatcher.update(deltaTime);
    }

    private void setUp(final Command callback) {
        //TODO moze jakas fabryka na tworzenie buttonow z uzyciem Command czy cos
        final TextButton leftClickButton = addButton(gameInputCatcher.getCurrentMouseMode().getTooltip());
        leftClickButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameInputCatcher.changeMouseMode();
            }
        });
        gameInputCatcher.addObserver(new ButtonListener(leftClickButton));
        final ImmutableArray<Entity> entities = engine
                .getEntitiesFor(Family.all(Position.class, Dimension.class, Renderable.class, Box2dBody.class).get());
        final Button clearBallsButton = addButton("Clear balls");
        clearBallsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (final Entity entity: entities) {
                    if (entity instanceof Ball) {
                        entity.add(ToRemove.self());
                    }
                }
            }
        });
        final Button callbackButton = addButton("menu");
        callbackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                callback.execute();
            }
        });
        final Table table = new Table(Assets.getSkin());
        table.add(leftClickButton);
        table.add(clearBallsButton);
        table.add(callbackButton).row();
        final ScrollPane debugPane = new ScrollPane(table, Assets.getSkin());
        debugPane.setSize(WIDTH / 2, HEIGHT / 10);
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
