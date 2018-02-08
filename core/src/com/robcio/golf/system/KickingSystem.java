package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Impulse;
import com.robcio.golf.component.Position;
import com.robcio.golf.component.Selected;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class KickingSystem extends IteratingSystem {

    public KickingSystem() {
        super(Family.all(Selected.class, Box2dBody.class).get());
        setProcessing(false);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Body body = Mapper.box2dBody.get(entity).body;
        final Position position = Selected.position;

        final Vector2 impulse = Maths.getDistance(body.getPosition(), new Vector2(position.x, position.y));

        entity.add(new Impulse(impulse.scl(MathUtils.clamp(impulse.len()*9f, 0f, 20f))));
    }
}
