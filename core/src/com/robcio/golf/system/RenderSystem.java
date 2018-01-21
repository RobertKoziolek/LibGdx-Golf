package com.robcio.golf.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.MainClass;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Position;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.utils.Maths;

//TODO po dodaniu wiekszej ilosci obiektow do rysowania nalezy rozdzielic czesc zmieniajaca pozycje i tu zostawic samo draw(batch)
public class RenderSystem extends IteratingSystem {
    final private ComponentMapper<Box2dBody> b2dm = ComponentMapper.getFor(Box2dBody.class);
    final private ComponentMapper<Renderable> rm = ComponentMapper.getFor(Renderable.class);
    final private ComponentMapper<Position> pm = ComponentMapper.getFor(Position.class);
    final private SpriteBatch batch;

    public RenderSystem(final SpriteBatch batch) {
        super(Family.all(Box2dBody.class, Renderable.class, Position.class).get());
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final Sprite sprite = rm.get(entity).sprite;
        final Body body = b2dm.get(entity).body;
        final Position position = pm.get(entity);
        final float radius = body.getFixtureList().get(0).getShape().getRadius() * MainClass.PPM;

        final Vector2 bodyPosition = body.getPosition();
        position.x = bodyPosition.x * MainClass.PPM;
        position.y = bodyPosition.y * MainClass.PPM;
        sprite.setPosition(position.x - radius, position.y - radius);
        sprite.setRotation(Maths.radiansToDegrees(body.getAngle()));
        sprite.draw(batch);
    }
}
