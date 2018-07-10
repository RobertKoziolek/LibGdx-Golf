package com.robcio.golf.system.control;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.util.Selected;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Impulse;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class KickingSystem extends IteratingSystem {

    public KickingSystem(final int priority) {
        super(Family.all(Selected.class, Kickable.class, Box2dBody.class).get(), priority);
        setProcessing(false);
    }

    @Override
    public void setProcessing(boolean processing) {
        super.setProcessing(false);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Body body = Mapper.box2dBody.get(entity).body;
        final Position position = Selected.position;

        final Vector2 impulse = Maths.getDistance(body.getPosition(), new Vector2(position.x, position.y));

        //TODO moze trzeba bedzie uzyc logarytmicznej funkcji log() w celu wyrownania sily, dodac wizualizacje
        entity.add(new Impulse(impulse.scl(MathUtils.clamp(impulse.len() * 9f, 0f, 20f))));
    }
}
