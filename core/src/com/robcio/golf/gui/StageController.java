package com.robcio.golf.gui;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Impulse;
import com.robcio.golf.listener.input.InputCatcher;
import com.robcio.golf.system.ImpulseSystem;
import com.robcio.golf.utils.Log;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;


public class StageController extends Stage {

    final private Skin skin;
    final private BitmapFont font32;
    final private InputCatcher inputCatcher;
    final private Engine engine;
    final AssetManager assets;

    public StageController(final Viewport viewport,
                           final InputCatcher inputCatcher,
                           final Engine engine) {
        super(viewport);
        this.inputCatcher = inputCatcher;
        this.engine = engine;
        assets = new AssetManager();
        font32 = loadFont();
        skin = loadSkin(font32);
        setUp();
    }

    private void setUp() {
        final TextButton leftClickButton = addButton("Creation/Attraction");
        leftClickButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final boolean creating = inputCatcher.changeBehaviour();
                leftClickButton.setText(creating ? "Attraction" : "Creation");
            }
        });
        final TextButton impulseButton = addButton("Impulse on/off");
        impulseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final boolean impulseOn = engine.getSystem(ImpulseSystem.class).change();
                impulseButton.setText(impulseOn ? "Impulse off" : "Impulse on");
            }
        });
        final ImmutableArray<Entity> entities = engine
                .getEntitiesFor(Family.all(Impulse.class, Box2dBody.class).get());
        Log.i("pilkow jest " + entities.size());
        final Button clearBallsButton = addButton("Clear balls");
        clearBallsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO czemu nie usuwa wszystkich tylko polowe?
                for (Entity entity : entities) {
                    engine.removeEntity(entity);
                }
            }
        });
        final Table table = new Table(skin);
        table.add(leftClickButton).row();
        table.add(impulseButton).row();
        table.add(clearBallsButton).row();
        final ScrollPane debugPane = new ScrollPane(table, skin);
        debugPane.setSize(WIDTH/3, HEIGHT / 12);
        debugPane.setScrollingDisabled(true, false);
        debugPane.setupFadeScrollBars(0f, 0f);
        debugPane.setupOverscroll(15, 10, 55);
        addActor(debugPane);
    }

    private TextButton addButton(final String text) {
        final int index = getActors().size;
        final TextButton button = new TextButton(text, skin);
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

    private BitmapFont loadFont() {
        return new BitmapFont(Gdx.files.internal("font/modak32.fnt"), Gdx.files.internal("font/modak32.png"),
                              false);
    }

    private Skin loadSkin(final BitmapFont font32) {
        assets.load("ui/uiskin.atlas", TextureAtlas.class);
        assets.finishLoading();
        final Skin skin = new Skin(assets.get("ui/uiskin.atlas", TextureAtlas.class));
        skin.add("default-font", font32);
        skin.load(Gdx.files.internal("ui/uiskin.json"));
        return skin;
    }

    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
        font32.dispose();
        assets.dispose();
    }
}
