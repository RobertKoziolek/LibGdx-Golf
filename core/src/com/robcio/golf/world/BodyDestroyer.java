package com.robcio.golf.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;

@AllArgsConstructor
public class BodyDestroyer {
    final private World world;
    final private Collection<Body> bodiesToDestroy = new LinkedList<>();

    public void clear() {
        for (final Body body : bodiesToDestroy) {
            world.destroyBody(body);
        }
        bodiesToDestroy.clear();
    }

    public void destroy(final Body body) {
        if (body == null) throw new IllegalArgumentException("Body scheduled to destroy cannot be null");
        bodiesToDestroy.add(body);
    }

}
