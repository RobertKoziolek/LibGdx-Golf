package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.Selected;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Line;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

import java.text.MessageFormat;
import java.util.Comparator;

public class LineRenderSystem extends IteratingSystem {

    final private ShapeRenderer shapeRenderer;

    public LineRenderSystem(final int priority, final Camera camera) {
        super(Family.all(Line.class).get(), priority);
        this.shapeRenderer = new ShapeRenderer();
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
        final Position end = Position.fromBox2D(line.end);

        //TODO tworzenie nowego obiektu w kazdej klatce jest raczej be
        shapeRenderer.line(start.getVector2(), end.getVector2());
    }
}
