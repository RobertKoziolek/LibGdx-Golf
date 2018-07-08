package com.robcio.golf.gui.game;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.ToRemove;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.gui.GuiAssembler;
import com.robcio.golf.listener.input.GameInputCatcher;
import com.robcio.golf.utils.Command;

import java.util.Observable;

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
        final TextButton changeMouseModeButton = getButton(gameInputCatcher.getCurrentMouseMode()
                                                                           .getTooltip(), getChangeMouseModeCommand(),
                                                           gameInputCatcher);
        final Button clearBallsButton = getButton("Clear balls", getClearBallsCommand());
        final Button callbackButton = getButton("menu", callback);

        final Table table = GuiAssembler.tableOf(changeMouseModeButton, clearBallsButton, callbackButton);
        final ScrollPane debugPane = GuiAssembler.paneOf(table)
                                                 .withSize(WIDTH / 2, HEIGHT / 10)
                                                 .withScrollingDisabled(true, false)
                                                 .withFadeScrollBars(0f, 0f)
                                                 .withOverscroll(15f, 10f, 55f)
                                                 .assemble();
        addActor(debugPane);
    }

    private TextButton getButton(final String text, final Command command) {
        return getButton(text, command, null);
    }

    private TextButton getButton(final String text, final Command command, final Observable observable) {
        final int index = getActors().size;
        final float width = WIDTH / 3;
        final float height = HEIGHT / 12;
        return GuiAssembler.textButtonOf(text)
                           .withSize(width, height)
                           .withPosition(width + width * ((index - 1) % 3),
                                         HEIGHT - height)
                           .withCommand(command)
                           .observing(observable)
                           .assemble();
    }

    private Command getChangeMouseModeCommand() {
        return new Command() {
            @Override
            public void execute() {
                gameInputCatcher.changeMouseMode();
            }
        };
    }

    private Command getClearBallsCommand() {
        final ImmutableArray<Entity> entities = engine
                .getEntitiesFor(Family.all(Position.class, Dimension.class, Renderable.class, Box2dBody.class)
                                      .get());
        return new Command() {
            @Override
            public void execute() {
                for (final Entity entity: entities) {
                    if (entity instanceof Ball) {
                        entity.add(ToRemove.self());
                    }
                }
            }
        };
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
