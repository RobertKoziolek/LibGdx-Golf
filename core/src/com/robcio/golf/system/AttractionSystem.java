package com.robcio.golf.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.InBowl;
import com.robcio.golf.component.Position;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class AttractionSystem extends IteratingSystem {

    public final Position position = Position.of(0.f, 0.f);

    public AttractionSystem() {
        super(Family.all(Box2dBody.class).get());
        setProcessing(false);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final Body body = Mapper.box2dBody.get(entity).body;

        Vector2 distance = new Vector2(0f, 0f);
        distance.add(body.getPosition());
        distance.sub(new Vector2(position.x, position.y));
        float finalDistance = distance.len();
        distance = new Vector2(-distance.x, -distance.y);

        float vecSum = Math.abs(distance.x) + Math.abs(distance.y);
        final int force = 1;

        if (vecSum < 0.6f) {
            body.setLinearVelocity(body.getLinearVelocity().scl(0.9f));
            body.setAngularVelocity(body.getAngularVelocity() * 0.9f);
        } else {
            distance = new Vector2(distance.x * (1/vecSum) * force * finalDistance,
                                   distance.y * (1/vecSum) * force * finalDistance);
            body.applyForceToCenter(distance, true);
        }
    }
}
