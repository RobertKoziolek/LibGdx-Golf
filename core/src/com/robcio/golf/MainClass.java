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
import com.robcio.golf.utils.Command;
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

        setScreen(ScreenId.MENU);

        //TODO moze sie zmienic jesli w menu ma byc fizyka
        Log.i("World body count *should be 0 now", Integer.toString(world.getBodyCount()));
    }

    public void setScreen(final ScreenId screenId){
        setScreen(screenRegistrar.get(screenId));
        setUpInput();
    }

    private void setUpInput() {
        final InputMultiplexer multiplexer = new InputMultiplexer(screenRegistrar.getCurrent().getInputs());
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void initializeRegistrars() {
        new EntityListenerRegistrar(engine, bodyDestroyer);
        new EntitySystemRegistrar(engine, batch, camera);
        screenRegistrar = new ScreenRegistrar(this, getMenuCallback(), world, engine, bodyDestroyer, camera);
    }

    private Command getMenuCallback() {
        return new Command() {
            @Override
            public void execute() {
                engine.removeAllEntities();
                setScreen(ScreenId.MENU);
            }
        };
    }

    private void initializeEngine() {
        engine = new Engine();
    }

    //TODO world i engine do wspolnej klasy bo w sumie tak pracuja, fasada here sie przyda
    private void initializeWorld() {
        world = new World(Vector2.Zero.cpy(), false);
        world.setContactListener(new Box2DContactListener());
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
        screenRegistrar.dispose();
        batch.dispose();
        world.dispose();
        Assets.dispose();
        Log.i("Disposing");
    }
}
