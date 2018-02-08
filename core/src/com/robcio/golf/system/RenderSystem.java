package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

import java.util.Comparator;

public class RenderSystem extends SortedIteratingSystem {

    final private SpriteBatch batch;

    public RenderSystem(final SpriteBatch batch) {
        super(Family.all(Box2dBody.class, Renderable.class, Position.class, Dimension.class).get(), new ZComparator());
        this.batch = batch;
    }

    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {
            return (int) Math.signum(Mapper.renderable.get(e1).z - Mapper.renderable.get(e2).z);
        }
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    //TODO moze ten angle wyciagnac do pozycji?
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final Sprite sprite = Mapper.renderable.get(entity).sprite;
        final Body body = Mapper.box2dBody.get(entity).body;
        final Position position = Mapper.position.get(entity);

        final float radius = Mapper.dimension.get(entity).getRadius1();
        final float radius2 = Mapper.dimension.get(entity).getRadius2();

        sprite.setRotation(Maths.radiansToDegrees(body.getAngle()));
        sprite.setPosition(position.x - radius, position.y - radius2);
        sprite.draw(batch);
    }
}
