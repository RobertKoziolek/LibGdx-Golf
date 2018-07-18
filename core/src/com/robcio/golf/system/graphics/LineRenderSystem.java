package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.robcio.golf.component.graphics.Line;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;

public class LineRenderSystem extends IteratingSystem {

    final private ShapeRenderer shapeRenderer;

    public LineRenderSystem(final int priority, final Camera camera) {
        super(Family.all(Line.class).get(), priority);
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.MAGENTA);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void update(float deltaTime) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        super.update(deltaTime);
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Line line = Mapper.line.get(entity);
        final Position start = line.start;
        final Position end = line.end;

        //TODO tworzenie nowego obiektu w kazdej klatce jest raczej be
        shapeRenderer.line(start.getVector2(), end.getVector2());
    }

    @Override
    public void removedFromEngine(final Engine engine) {
        Log.i("Disposing of line renderer");
        shapeRenderer.dispose();
    }
}
