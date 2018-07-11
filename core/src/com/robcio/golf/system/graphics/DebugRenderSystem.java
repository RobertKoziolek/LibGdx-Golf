package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.robcio.golf.component.graphics.WorldAndCamera;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class DebugRenderSystem extends IteratingSystem {

    private final Box2DDebugRenderer box2DDebugRenderer;

    public DebugRenderSystem(final int priority) {
        super(Family.all(WorldAndCamera.class)
                    .get(), priority);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final WorldAndCamera worldAndCamera = Mapper.worldAndCamera.get(entity);
        box2DDebugRenderer.render(worldAndCamera.world, worldAndCamera.camera.combined.scl(Maths.PPM));
    }

    @Override
    public void removedFromEngine(final Engine engine) {
        Log.i("Disposing of box2d debug renderer");
        box2DDebugRenderer.dispose();
    }
}
