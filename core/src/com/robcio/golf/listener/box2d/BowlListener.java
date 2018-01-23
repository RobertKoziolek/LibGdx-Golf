package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Log;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
class BowlListener {

    final private Engine engine;

    void beginContact(final Map<Integer, Body> map) {
        final Body ball = map.get(EntityFlags.BALL.getId());
        final Body bowl = map.get(EntityFlags.BOWL.getId());

        Vector2 distance = new Vector2(0f, 0f);
        distance.add(ball.getPosition());
        distance.sub(bowl.getPosition());
        float finalDistance = distance.len();
        distance = new Vector2(-distance.x, -distance.y);

        float vecSum = Math.abs(distance.x) + Math.abs(distance.y);
        final int force = 33;
        distance = new Vector2(distance.x * (1 / vecSum) * force / finalDistance,
                               distance.y * (1 / vecSum) * force / finalDistance);
        ball.applyForceToCenter(distance, true);
    }
}