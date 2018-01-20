package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Box2dBody implements Component {
    public Body body;
}
