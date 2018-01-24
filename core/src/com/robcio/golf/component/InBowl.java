package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.badlogic.gdx.math.Vector2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InBowl implements Component {
    public Vector2 bowlCenter;
    public Dimension bowlDimension;
}
