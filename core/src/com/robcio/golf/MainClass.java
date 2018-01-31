package com.robcio.golf;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.golf.gui.StageController;
import com.robcio.golf.listener.Box2DContactListener;
import com.robcio.golf.listener.input.InputCatcher;
import com.robcio.golf.map.Map;
import com.robcio.golf.registrar.EntityListenerRegistrar;
import com.robcio.golf.registrar.EntitySystemRegistrar;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Maths;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyDestroyer;
import com.robcio.golf.world.BodyFactory;

public class MainClass extends Game {
    public static final int WIDTH = (int) (16 * Maths.PPM);
    public static final int HEIGHT = (int) (9 * Maths.PPM);
    public static final String TITLE = "Golf z Tibii";

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private StageController stageController;

    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthogonalTiledMapRenderer mapRenderer;

    private World world;
    private BodyDestroyer bodyDestroyer;
    private Engine engine;

    private final boolean DEBUG;

    public MainClass(final boolean isDebugOn){
        this.DEBUG = isDebugOn;
    }

    //TODO ogarnac ten syf
    @Override
    public void create() {
        box2DDebugRenderer = new Box2DDebugRenderer();

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

//        new TestMap(engine);
        final Map map = new Map(engine);
        mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        mapRenderer.setView(camera);


        final InputCatcher inputCatcher = new InputCatcher(camera, engine);

        stageController = new StageController(new FillViewport(WIDTH, HEIGHT, camera), inputCatcher, engine);
        final InputMultiplexer multiplexer = new InputMultiplexer(stageController, inputCatcher);
        Gdx.input.setInputProcessor(multiplexer);
        Log.i("World body count", Integer.toString(world.getBodyCount()));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();


        update(Gdx.graphics.getDeltaTime());
        mapRenderer.render();

//        batch.begin();
//        batch.end();

        if (DEBUG) box2DDebugRenderer.render(world, camera.combined.scl(Maths.PPM));

        stageController.draw();
    }

    private void update(final float deltaTime) {
        world.step(1 / 60f, 6, 2);
        engine.update(deltaTime);
        bodyDestroyer.clear();
        stageController.act(deltaTime);
    }

    @Override
    public void dispose() {
        batch.dispose();
        Textures.dispose();
        world.dispose();
        stageController.dispose();
        mapRenderer.dispose();
        Log.i("Disposing");
    }
}
