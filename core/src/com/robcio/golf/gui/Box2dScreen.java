package com.robcio.golf.gui;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.enumeration.MapId;
import com.robcio.golf.map.MapReader;
import com.robcio.golf.utils.Maths;
import com.robcio.golf.world.BodyDestroyer;

import static com.robcio.golf.MainClass.DEBUG;

public abstract class Box2dScreen extends AbstractScreen {

    private final World world;
    private final Engine engine;
    private final BodyDestroyer bodyDestroyer;
    private final Camera camera;

    //TODO te renderery tez raczej do systemow wywalic, nieladnie ze sa tu
    private final Box2DDebugRenderer box2DDebugRenderer;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final MapReader mapReader;

    public Box2dScreen(final World world, final Engine engine, final BodyDestroyer bodyDestroyer, final Camera camera) {
        super();
        this.world = world;
        this.engine = engine;
        this.bodyDestroyer = bodyDestroyer;
        this.camera = camera;

        this.mapReader = new MapReader(engine);

        box2DDebugRenderer = new Box2DDebugRenderer();
        mapRenderer = new OrthogonalTiledMapRenderer(mapReader.getCurrent());
        try {
            mapRenderer.setView((OrthographicCamera) camera);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException("Camera must be orthographic");
        }
    }

    public void setMap(final MapId map) {
        mapReader.load(map);
        mapRenderer.setMap(mapReader.getCurrent());
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        mapRenderer.render();

        if (DEBUG) box2DDebugRenderer.render(world, camera.combined.scl(Maths.PPM));

        getStage().draw();
    }

    @Override
    public void update(final float deltaTime) {
        super.update(deltaTime);
        world.step(1 / 60f, 6, 2);
        engine.update(deltaTime);
        bodyDestroyer.clear();
    }

    @Override
    public void dispose() {
        super.dispose();
        mapRenderer.dispose();
        box2DDebugRenderer.dispose();
    }
}
