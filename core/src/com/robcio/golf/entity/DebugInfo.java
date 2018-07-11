package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.component.graphics.WorldAndCamera;

public class DebugInfo extends Entity {

    public DebugInfo(final Camera camera, final World world) {
        add(new WorldAndCamera(camera, world));
    }
}