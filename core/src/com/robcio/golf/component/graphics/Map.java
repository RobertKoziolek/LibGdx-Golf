package com.robcio.golf.component.graphics;

import com.badlogic.ashley.core.Component;
import com.robcio.golf.enumeration.MapId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Map implements Component {
    final public MapId mapId;
}
