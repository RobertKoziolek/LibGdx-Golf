package com.robcio.golf;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Force;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.*;
import com.robcio.golf.listener.Box2DContactListener;
import com.robcio.golf.listener.entity.Box2DBodyRemover;
import com.robcio.golf.listener.entity.SpriteAssigner;
import com.robcio.golf.registrar.EntityListenerRegistrar;
import com.robcio.golf.registrar.EntitySystemRegistrar;
import com.robcio.golf.system.ImpulseSystem;
import com.robcio.golf.system.RenderSystem;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyDestroyer;
import com.robcio.golf.world.BodyFactory;

public class MainClass extends Game {
    public static final float PPM = 64;
    public static final int WIDTH = (int) (16 * PPM);
    public static final int HEIGHT = (int) (9 * PPM);
    public static final String TITLE = "Golf z Tibii";

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private AssetManager assets;

    private Box2DDebugRenderer b2dr;

    private World world;
    private BodyDestroyer bodyDestroyer;
    private Engine engine;

    private final boolean DEBUG;

    public MainClass(final boolean isDebugOn){
        this.DEBUG = isDebugOn;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        batch.enableBlending();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);
//        batch.setProjectionMatrix(camera.combined);
        b2dr = new Box2DDebugRenderer();

//        assets = new AssetManager();

        engine = new Engine();
        world = new World(new Vector2(0f, 0f), false);
        BodyFactory.setWorld(world);
        bodyDestroyer = new BodyDestroyer(world);

        world.setContactListener(new Box2DContactListener(engine));
        new EntityListenerRegistrar(engine, bodyDestroyer);
        new EntitySystemRegistrar(engine, batch);

        createBoundaries();
        createEntities();
        Log.i("World body count", Integer.toString(world.getBodyCount()));
    }

    private void createEntities() {
        for (int i = 0; i < 55; ++i) {
            engine.addEntity(new Ball(Position.of(WIDTH / 2, HEIGHT / 2), Dimension.of(15)));
        }

//        engine.addEntity(new Hole(Position.of(50, 150), Dimension.of(16)));
//        engine.addEntity(new Hole(Position.of(350, 350), Dimension.of(16)));
//        engine.addEntity(new Hole(Position.of(600, 450), Dimension.of(16)));

        engine.addEntity(new Bowl(Position.of(800, 350), Dimension.of(25)));
        engine.addEntity(new Hole(Position.of(800, 350), Dimension.of(0.5f)));

        engine.addEntity(new Bumper(Position.of(200, 400), Dimension.of(30), Force.of(55)));
        engine.addEntity(new Bumper(Position.of(200, 300), Dimension.of(30), Force.of(55)));
        engine.addEntity(new Bumper(Position.of(200, 200), Dimension.of(30), Force.of(55)));

//        BodyFactory.createBox(Position.of(200, 200), Dimension.of(50, 99), false, false, 2, 3);
//        BodyFactory.createBox(Position.of(211, 400), Dimension.of(140, 49), false, false, 2, 3);
//        BodyFactory.createCircular(Position.of(773, 500), Dimension.of(50, 89), false, false, 2, 3);
//        BodyFactory.createCircular(Position.of(473, 500), Dimension.of(50, 50), false, false, 2, 3);
    }

    private void createBoundaries() {
        //TODO popatrzyc na EdgeShape czy nie lepszy do tego
        new Wall(Position.of(WIDTH / 2, 9), Dimension.of(WIDTH, 9));
        new Wall(Position.of(WIDTH / 2, HEIGHT - 9), Dimension.of(WIDTH, 9));
        new Wall(Position.of(9, HEIGHT / 2), Dimension.of(9, HEIGHT));
        new Wall(Position.of(WIDTH - 9, HEIGHT / 2), Dimension.of(9, HEIGHT));

//        new Wall(Position.of(WIDTH / 2, HEIGHT / 2), Dimension.of(9, HEIGHT - 299));
//        new Wall(Position.of(WIDTH / 2 - 299, HEIGHT / 2), Dimension.of(9, HEIGHT - 299));
//        new Wall(Position.of(WIDTH / 2 - 149, HEIGHT / 2 + 99), Dimension.of(299, 9));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        update(Gdx.graphics.getDeltaTime());

//        batch.begin();
//        batch.end();
        if (DEBUG)b2dr.render(world, camera.combined);
    }

    private void update(final float deltaTime) {
        world.step(1 / 60f, 6, 2);
        engine.update(deltaTime);
        bodyDestroyer.clear();
    }

    @Override
    public void dispose() {
        batch.dispose();
        Textures.dispose();
        world.dispose();
        Log.i("Disposing");
    }
}
