package com.robcio.golf.system.graphics;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.robcio.golf.utils.Maths;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;

public class LightRenderSystem extends EntitySystem {

    private final RayHandler rayHandler;
    private final OrthographicCamera b2dCam;

    public LightRenderSystem(final int priority, final RayHandler rayHandler) {
        super(priority);
        b2dCam = new OrthographicCamera(WIDTH, HEIGHT);
        b2dCam.setToOrtho(false);
        this.rayHandler = rayHandler;
        rayHandler.setBlurNum(9);
        rayHandler.setCombinedMatrix(b2dCam.combined.scale(Maths.PPM, Maths.PPM, Maths.PPM),
                                     b2dCam.position.x * 1 / Maths.PPM, b2dCam.position.y * 1 / Maths.PPM,
                                     b2dCam.viewportWidth * b2dCam.zoom, b2dCam.viewportHeight * b2dCam.zoom);
        rayHandler.setShadows(true);
        //TODO ustawianie lightu dla mapy, tak jak grawitacja, generalnie jakies mapinfo przy mapLoaderze
        rayHandler.setAmbientLight(0.4f, 0.4f, 0.4f, 0.65f);
    }

    @Override
    public void update(final float deltaTime) {
        rayHandler.updateAndRender();
        b2dCam.update();
    }
}
