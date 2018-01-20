package com.robcio.golf;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.entity.Wall;
import com.robcio.golf.listener.BallListener;
import com.robcio.golf.system.ImpulseSystem;
import com.robcio.golf.system.RenderSystem;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.TextureManager;
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
    private Engine engine;

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);
//        batch.setProjectionMatrix(camera.combined);
        b2dr = new Box2DDebugRenderer();

//        assets = new AssetManager();
        createBoundaries();
    }

    private void createBoundaries() {
        world = BodyFactory.getWorld();
        engine = new Engine();
        engine.addEntityListener(Family.all(Renderable.class, Box2dBody.class).get(), new BallListener());
        new Wall(Position.of(WIDTH / 2, 50), Dimension.of(WIDTH, 10));
        new Wall(Position.of(WIDTH / 2, HEIGHT - 50), Dimension.of(WIDTH, 10));
        new Wall(Position.of(50, HEIGHT / 2), Dimension.of(10, HEIGHT));
        new Wall(Position.of(WIDTH - 50, HEIGHT / 2), Dimension.of(10, HEIGHT));


        BodyFactory.createBox(200, 200, 50, 99, false, false, 2, 3);
        BodyFactory.createBox(211, 400, 140, 49, false, false, 2, 3);
        BodyFactory.createBox(773, 500, 50, 89, false, false, 2, 3);
        BodyFactory.createBox(473, 500, 50, 49, false, false, 2, 3);
        for (int i = 0; i < 41; ++i)
            engine.addEntity(new Ball(Position.of(WIDTH / 2, HEIGHT / 2), Dimension.of(15)));

        engine.addSystem(new ImpulseSystem(1.5f));
        engine.addSystem(new RenderSystem(batch));

        Log.i("World body count", Integer.toString(world.getBodyCount()));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        update(Gdx.graphics.getDeltaTime());

//        batch.begin();
//        batch.end();
        b2dr.render(world, camera.combined);
    }

    private void update(final float deltaTime) {
        world.step(1 / 60f, 6, 2);
        engine.update(deltaTime);
    }

    @Override
    public void dispose() {
        batch.dispose();
        TextureManager.clear();
        Log.i("Disposing");
    }
}
