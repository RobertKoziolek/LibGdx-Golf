package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.*;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class KickToSystem extends IteratingSystem {

    public KickToSystem() {
        super(Family.all(Kickable.class, Box2dBody.class).get());
        setProcessing(false);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Body body = Mapper.box2dBody.get(entity).body;
        final Position position = Selected.position;

        final Vector2 impulse = Maths.getDistance(new Vector2(position.x, position.y), body.getPosition());
        if (!Mapper.selected.has(entity)) {
            entity.add(new Selected());
            Log.i("tibia");
        }
        //TODO moze trzeba bedzie uzyc logarytmicznej funkcji log() w celu wyrownania sily, dodac wizualizacje
        if (Mapper.selected.has(entity)) {
            entity.add(new Impulse(impulse.scl(MathUtils.clamp(impulse.len() * 9f, 8f, 20f))));
        }
    }
}
