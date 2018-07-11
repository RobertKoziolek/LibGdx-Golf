package com.robcio.golf.gui;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.entity.util.Do;
import com.robcio.golf.entity.util.LoadMap;
import com.robcio.golf.enumeration.MapId;
import com.robcio.golf.utils.Command;
import com.robcio.golf.world.BodyDestroyer;

public abstract class Box2dScreen extends AbstractScreen {

    private final World world;
    private final Engine engine;
    private final BodyDestroyer bodyDestroyer;

    public Box2dScreen(final World world, final Engine engine, final BodyDestroyer bodyDestroyer) {
        super();
        this.world = world;
        this.engine = engine;
        this.bodyDestroyer = bodyDestroyer;
    }

    protected void loadMap(final MapId map, final Command command) {
        engine.addEntity(new LoadMap(map));
        engine.addEntity(new Do(command));
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);

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
    }
}
