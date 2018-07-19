package com.robcio.golf.component.flag;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.structure.Position;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Tether implements Component {
    final public Position position1, position2;
    final public Entity tetheredBy;
}
