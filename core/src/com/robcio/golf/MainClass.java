package com.robcio.golf;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.enumeration.ScreenId;
import com.robcio.golf.listener.Box2DContactListener;
import com.robcio.golf.registrar.EntityListenerRegistrar;
import com.robcio.golf.registrar.EntitySystemRegistrar;
import com.robcio.golf.registrar.ScreenRegistrar;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Maths;
import com.robcio.golf.world.BodyDestroyer;
import com.robcio.golf.world.BodyFactory;

public class MainClass extends Game {
    //TODO lepszy system zalaczania debuga
    public static boolean DEBUG;

    public static final int WIDTH = (int) (16 * Maths.PPM);
    public static final int HEIGHT = (int) (9 * Maths.PPM);
    public static final String TITLE = "Golf z Tibii";

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Engine engine;
    private World world;
    private BodyDestroyer bodyDestroyer;

    private ScreenRegistrar screenRegistrar;

    public MainClass(final boolean isDebugOn) {
        DEBUG = isDebugOn;
    }

    @Override
    public void create() {
        Assets.initialize();
        initializeCamera();
        initializeBatch();
        initializeEngine();
        initializeWorld();
        initializeRegistrars();

        //TODO zalaczanie screenow kiedy sie ich pojawi wiecej
        {
            setScreen(screenRegistrar.get(ScreenId.GAME));
            setUpInput();
        }

        Log.i("World body count", Integer.toString(world.getBodyCount()));
    }

    private void setUpInput() {
        final InputMultiplexer multiplexer = new InputMultiplexer(screenRegistrar.getCurrent().getInputs());
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void initializeRegistrars() {
        new EntityListenerRegistrar(engine, bodyDestroyer);
        new EntitySystemRegistrar(engine, batch);
        screenRegistrar = new ScreenRegistrar(world, engine, bodyDestroyer, camera);
    }

    private void initializeEngine() {
        engine = new Engine();
    }

    //TODO world i engine do wspolnej klasy bo w sumie tak pracuja, fasada here sie przyda
    private void initializeWorld() {
        world = new World(new Vector2(0f, 0f), false);
        world.setContactListener(new Box2DContactListener(engine));
        BodyFactory.setWorld(world);
        bodyDestroyer = new BodyDestroyer(world);
    }

    private void initializeBatch() {
        batch = new SpriteBatch();
        batch.enableBlending();
        batch.setProjectionMatrix(camera.combined);
    }

    private void initializeCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
    }

    @Override
    public void render() {
        super.render();
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        Assets.dispose();
        Log.i("Disposing");
    }
}
