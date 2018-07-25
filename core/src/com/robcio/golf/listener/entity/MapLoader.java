package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.robcio.golf.component.graphics.Map;
import com.robcio.golf.map.MapReader;
import com.robcio.golf.system.graphics.MapRenderSystem;
import com.robcio.golf.utils.Mapper;

public class MapLoader implements EntityListener {

    final public static Family family = Family.all(Map.class)
                                              .get();

    private final Engine engine;
    private final MapReader mapReader;

    public MapLoader(final Engine engine) {
        this.engine = engine;
        this.mapReader = new MapReader(engine);
    }

    @Override
    public void entityAdded(final Entity entity) {
        final Map map = Mapper.map.get(entity);
        final TiledMap loadedMap = mapReader.load(map.mapId);
        final MapRenderSystem system = engine.getSystem(MapRenderSystem.class);
        system.setMap(loadedMap);
        system.setProcessing(true);
    }

    @Override
    public void entityRemoved(final Entity entity) {
        engine.getSystem(MapRenderSystem.class)
              .setProcessing(false);
    }
}
