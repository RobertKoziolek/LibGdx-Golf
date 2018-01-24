package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Impulse;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.Ball;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InputCatcher implements InputProcessor {

    private final OrthographicCamera camera;

    private final Engine engine;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.C){
            //TODO czemu nie usuwa wszystkich tylko czesc?
            final ImmutableArray<Entity> entities = engine
                    .getEntitiesFor(Family.all(Impulse.class, Box2dBody.class).get());
            for (Entity entity : entities){
                engine.removeEntity(entity);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        engine.addEntity(new Ball(getUnprojectedPosition(screenX, screenY), Dimension.of(15)));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        engine.addEntity(new Ball(getUnprojectedPosition(screenX, screenY), Dimension.of(15)));
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private Position getUnprojectedPosition(final int screenX, final int screenY){
        final Vector3 realCoords = camera.unproject(new Vector3(screenX, screenY, 0f));
        return Position.of(realCoords.x, realCoords.y);
    }
}
