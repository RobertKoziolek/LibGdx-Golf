package com.robcio.golf.component.util;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class InGroup implements Component {
    final public Entity groupedBy;
}
