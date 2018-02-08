package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

import java.util.Comparator;

public class PositionSynchronizationSystem extends IteratingSystem {


    public PositionSynchronizationSystem() {
        super(Family.all(Box2dBody.class, Position.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final Body body = Mapper.box2dBody.get(entity).body;
        final Position position = Mapper.position.get(entity);

        final Vector2 scaledPosition = body.getPosition().cpy().scl(Maths.PPM);
        position.set(scaledPosition);
    }
}
