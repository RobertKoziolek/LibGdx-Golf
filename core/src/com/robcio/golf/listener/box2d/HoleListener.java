package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Log;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
class HoleListener {

    final private Engine engine;

    void beginContact(final Map<Integer, Body> map) {
        final Entity ballEntity = (Entity) map.get(EntityFlags.BALL.getId()).getUserData();
        engine.removeEntity(ballEntity);
    }
}
