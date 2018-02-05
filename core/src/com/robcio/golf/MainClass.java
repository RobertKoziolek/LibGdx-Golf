package com.robcio.golf;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.robcio.golf.gui.game.GameScreen;
import com.robcio.golf.gui.game.StageController;
import com.robcio.golf.listener.Box2DContactListener;
import com.robcio.golf.map.Map;
import com.robcio.golf.registrar.EntityListenerRegistrar;
import com.robcio.golf.registrar.EntitySystemRegistrar;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Maths;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyDestroyer;
import com.robcio.golf.world.BodyFactory;

public class MainClass extends Game {
    //TODO lepszy system zalaczania debuga
    public static boolean DEBUG;

    final AssetManager assets = new AssetManager();
    //TODO wymyslic cos sensownego z tym skinem itd
    static public Skin skin;
    private BitmapFont font32;

    public static final int WIDTH = (int) (16 * Maths.PPM);
    public static final int HEIGHT = (int) (9 * Maths.PPM);
    public static final String TITLE = "Golf z Tibii";

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private World world;
    private BodyDestroyer bodyDestroyer;
    private Engine engine;


    public MainClass(final boolean isDebugOn){
        DEBUG = isDebugOn;
    }

    //TODO ogarnac ten syf
    @Override
    public void create() {
        font32 = loadFont();
        skin = loadSkin(font32);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        batch = new SpriteBatch();
        batch.enableBlending();
        batch.setProjectionMatrix(camera.combined);

        world = new World(new Vector2(0f, 0f), false);
        BodyFactory.setWorld(world);
        bodyDestroyer = new BodyDestroyer(world);

        engine = new Engine();

        world.setContactListener(new Box2DContactListener(engine));
        new EntityListenerRegistrar(engine, bodyDestroyer);
        new EntitySystemRegistrar(engine, batch);

        final GameScreen gameScreen = new GameScreen(world, engine, bodyDestroyer, camera);
        setScreen(gameScreen);

        final InputMultiplexer multiplexer = new InputMultiplexer(gameScreen.getInputs());
        Gdx.input.setInputProcessor(multiplexer);
        Log.i("World body count", Integer.toString(world.getBodyCount()));
    }

    @Override
    public void render() {
        super.render();
//        camera.update();

    }

    @Override
    public void dispose() {
        batch.dispose();
        Textures.dispose();
        world.dispose();
        skin.dispose();
        font32.dispose();
        assets.dispose();
        Log.i("Disposing");
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
}
