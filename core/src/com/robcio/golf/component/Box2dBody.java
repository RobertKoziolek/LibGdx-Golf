package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class Box2dBody implements Component {
    public Body body;

    public Box2dBody(final Body body) {
        if (body == null) throw new IllegalArgumentException("Body cannot be null");
        this.body = body;
    }
}
