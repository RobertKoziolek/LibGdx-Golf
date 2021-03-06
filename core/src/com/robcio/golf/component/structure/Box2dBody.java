package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import lombok.NonNull;

public class Box2dBody implements Component {
    public Body body;

    public Box2dBody(@NonNull final Body body) {
        this.body = body;
    }

    public static Box2dBody of(final Body body) {
        return new Box2dBody(body);
    }
}
