package com.robcio.golf.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.utils.Log;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class BodyDestroyer {
    final private World world;
    final private Collection<Body> bodiesToDestroy = new LinkedList<>();

    public void clear(){
        for (final Body body : bodiesToDestroy){
            world.destroyBody(body);
            Log.i("Box2d", "Destroying body for " + body);
        }
        bodiesToDestroy.clear();
    }

    public void destroy(final Body body){
        if (body != null) bodiesToDestroy.add(body);
    }

}
