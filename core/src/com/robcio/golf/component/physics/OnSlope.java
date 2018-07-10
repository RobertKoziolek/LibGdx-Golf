package com.robcio.golf.component.physics;

import com.badlogic.ashley.core.Component;
import com.robcio.golf.component.structure.Direction;
import com.robcio.golf.component.structure.Force;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OnSlope implements Component {
    public Direction direction;
    public Force force;
}
