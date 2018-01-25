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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.robcio.golf.MainClass;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Impulse;
import com.robcio.golf.listener.input.InputCatcher;
import com.robcio.golf.system.ImpulseSystem;

public class StageController extends Stage {

    final private Skin skin;
    final private BitmapFont font32;
    final private InputCatcher inputCatcher;
    final private Engine engine;

    public StageController(final Viewport viewport,
                           final AssetManager assets,
                           final InputCatcher inputCatcher,
                           final Engine engine) {
        super(viewport);
        this.inputCatcher = inputCatcher;
        this.engine = engine;
        font32 = loadFont();
        skin = loadSkin(assets, font32);

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
        final Button clearBallsButton = addButton("Clear balls");
        clearBallsButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO czemu nie usuwa wszystkich tylko polowe?
                for (Entity entity : entities) {
                    engine.removeEntity(entity);
                }
            }
        });

    }

    private TextButton addButton(final String text) {
        final int index = getActors().size;
        final TextButton button = new TextButton(text, skin);
        final float width = MainClass.WIDTH / numberOfButtonsInRow();
        final float height = MainClass.HEIGHT / 12;
        button.setSize(width, height);
        button.setPosition(width + width * ((index - 1) % numberOfButtonsInRow()),
                           MainClass.HEIGHT - height);
        addActor(button);
        return button;
    }

    private int numberOfButtonsInRow() {
        return 3;
    }

    private BitmapFont loadFont() {
        return new BitmapFont(Gdx.files.internal("font/modak32.fnt"), Gdx.files.internal("font/modak32.png"),
                              true);
    }

    private Skin loadSkin(final AssetManager assets, final BitmapFont font32) {
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
    }
}
