package com.robcio.golf.gui;

import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.world.BodyDestroyer;

public abstract class Box2dScreen extends AbstractScreen {

    private final World world;
    private final BodyDestroyer bodyDestroyer;

    public Box2dScreen(final World world, final BodyDestroyer bodyDestroyer) {
        super();
        this.world = world;
        this.bodyDestroyer = bodyDestroyer;
    }

    @Override
    public void render(final float delta) {
        update(delta);
        getStage().draw();
    }

    @Override
    public void update(final float deltaTime) {
        super.update(deltaTime);
        world.step(1 / 60f, 6, 2);
        bodyDestroyer.clear();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
