package com.robcio.golf.component.graphics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WorldAndCamera implements Component {
    final public Camera camera;
    final public World world;
}
