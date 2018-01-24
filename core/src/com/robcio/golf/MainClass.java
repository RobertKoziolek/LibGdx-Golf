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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Force;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.*;
import com.robcio.golf.listener.Box2DContactListener;
import com.robcio.golf.listener.input.InputCatcher;
import com.robcio.golf.registrar.EntityListenerRegistrar;
import com.robcio.golf.registrar.EntitySystemRegistrar;
import com.robcio.golf.system.ImpulseSystem;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Maths;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyDestroyer;
import com.robcio.golf.world.BodyFactory;

import java.util.Random;

public class MainClass extends Game {
    public static final int WIDTH = (int) (16 * Maths.PPM);
    public static final int HEIGHT = (int) (9 * Maths.PPM);
    public static final String TITLE = "Golf z Tibii";

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Stage stage;
    private AssetManager assets;
    private Skin skin;

    private Box2DDebugRenderer b2dr;

    private World world;
    private BodyDestroyer bodyDestroyer;
    private Engine engine;

    private final boolean DEBUG;
    private BitmapFont font32;

    public MainClass(final boolean isDebugOn){
        this.DEBUG = isDebugOn;
    }

    //TODO ogarnac ten syf
    @Override
    public void create() {
        assets = new AssetManager();
        assets.load("ui/uiskin.atlas", TextureAtlas.class);
        assets.finishLoading();
        b2dr = new Box2DDebugRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(true, WIDTH, HEIGHT);

        final Viewport viewport = new FillViewport(WIDTH, HEIGHT, camera);
        stage = new Stage(viewport);



        skin = new Skin(assets.get("ui/uiskin.atlas", TextureAtlas.class));
        font32 = new BitmapFont(Gdx.files.internal("font/modak32.fnt"), Gdx.files.internal("font/modak32.png"),
                                false);
        skin.add("default-font", font32);
        skin.load(Gdx.files.internal("ui/uiskin.json"));
        batch = new SpriteBatch();
        batch.enableBlending();
        batch.setProjectionMatrix(camera.combined);

//        assets = new AssetManager();

        world = new World(new Vector2(0f, 0f), false);
        BodyFactory.setWorld(world);
        bodyDestroyer = new BodyDestroyer(world);

        engine = new Engine();

        world.setContactListener(new Box2DContactListener(engine));
        new EntityListenerRegistrar(engine, bodyDestroyer);
        new EntitySystemRegistrar(engine, batch);

        createBoundaries();
        createEntities();
        final InputCatcher inputCatcher = new InputCatcher(camera, engine);

        final TextButton button = new TextButton("Creation/Attraction", skin);
        button.setWidth(WIDTH/2);
        button.setHeight(HEIGHT/20);
        stage.addActor(button);
        final TextButton button2 = new TextButton("Impulse on/off", skin);
        button2.setWidth(WIDTH/2);
        button2.setHeight(HEIGHT/20);
        button2.setPosition(WIDTH/2, 0f);
        stage.addActor(button2);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inputCatcher.changeBehaviour();
            }
        });
        button2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                engine.getSystem(ImpulseSystem.class).change();
            }
        });
        final InputMultiplexer multiplexer = new InputMultiplexer(stage, inputCatcher);
        Gdx.input.setInputProcessor(multiplexer);
        Log.i("World body count", Integer.toString(world.getBodyCount()));
    }

    private void createEntities() {
        for (int i = 0; i < 1; ++i) {
            engine.addEntity(new Ball(Position.of(WIDTH / 2, HEIGHT / 2), Dimension.of(15)));
        }

//        engine.addEntity(new Hole(Position.of(50, 150), Dimension.of(16)));
//        engine.addEntity(new Hole(Position.of(350, 350), Dimension.of(16)));
//        engine.addEntity(new Hole(Position.of(600, 450), Dimension.of(16)));

        addHole(Position.of(400, 500));
        addHole(Position.of(55, 500));
        addHole(Position.of(400, 100));
        addHole(Position.of(55, 100));
        engine.addEntity(new Bowl(Position.of(700, 300), Dimension.of(199), Textures.BOWL));

        final Force bumperForce =  Force.of(55);
        final Dimension bumperDimension = Dimension.of(20);

        engine.addEntity(new Bumper(Position.of(200, 500), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(200, 400), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(200, 300), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(200, 200), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(200, 100), bumperDimension, bumperForce));

        engine.addEntity(new Bumper(Position.of(300, 150), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(300, 250), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(300, 350), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(300, 450), bumperDimension, bumperForce));

        engine.addEntity(new Bumper(Position.of(100, 150), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(100, 250), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(100, 350), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(100, 450), bumperDimension, bumperForce));

//        BodyFactory.createBox(Position.of(200, 200), Dimension.of(50, 99), false, false, 2, 3);
//        BodyFactory.createBox(Position.of(211, 400), Dimension.of(140, 49), false, false, 2, 3);
//        BodyFactory.createCircular(Position.of(773, 500), Dimension.of(50, 89), false, false, 2, 3);
//        BodyFactory.createCircular(Position.of(473, 500), Dimension.of(50, 50), false, false, 2, 3);
    }

    private void addHole(final Position position) {
        engine.addEntity(new Bowl(position, Dimension.of(25), Textures.HOLE));
        engine.addEntity(new Hole(position, Dimension.of(0.5f)));
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
        if (DEBUG)b2dr.render(world, camera.combined.scl(Maths.PPM));

        stage.draw();
    }

    private void update(final float deltaTime) {
        world.step(1 / 60f, 6, 2);
        engine.update(deltaTime);
        bodyDestroyer.clear();
        stage.act(deltaTime);
    }

    @Override
    public void dispose() {
        batch.dispose();
        Textures.dispose();
        world.dispose();
        stage.dispose();
        skin.dispose();
        Log.i("Disposing");
    }
}
