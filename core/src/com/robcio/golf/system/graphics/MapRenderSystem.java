package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.robcio.golf.component.graphics.Map;
import com.robcio.golf.utils.Log;

public class MapRenderSystem extends IteratingSystem {

    private final OrthogonalTiledMapRenderer mapRenderer;

    public MapRenderSystem(final int priority, final Camera camera) {
        super(Family.all(Map.class)
                    .get(), priority);
        this.mapRenderer = new OrthogonalTiledMapRenderer(null);
        try {
            mapRenderer.setView((OrthographicCamera) camera);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException("Camera must be orthographic");
        }
        setProcessing(false);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        mapRenderer.render();
    }

    @Override
    public void removedFromEngine(final Engine engine) {
        Log.i("Disposing of map renderer");
        mapRenderer.dispose();
    }

    public void setMap(final TiledMap loadedMap) {
        mapRenderer.setMap(loadedMap);
    }
}
