package com.robcio.golf.entity.util;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.graphics.Map;
import com.robcio.golf.enumeration.MapId;

public class LoadMap extends Entity {

    public LoadMap(final MapId tiledMap) {
        add(new Map(tiledMap));
    }
}