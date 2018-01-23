package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.robcio.golf.MainClass;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.utils.Log;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InputCatcher implements InputProcessor {

    private final OrthographicCamera camera;

    private final Engine engine;

    @Override
    public boolean keyDown(int keycode) {
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
        final Vector3 realCoords = camera.unproject(new Vector3(screenX, screenY, 0f));
        final int x = (int) (realCoords.x * MainClass.PPM);
        final int y = (int) (realCoords.y * MainClass.PPM);
        Log.i(String.format("Touched at %d, %d", x, y));
        engine.addEntity(new Ball(Position.of(x, y), Dimension.of(15)));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        final Vector3 realCoords = camera.unproject(new Vector3(screenX, screenY, 0f));
        final int x = (int) (realCoords.x * MainClass.PPM);
        final int y = (int) (realCoords.y * MainClass.PPM);
        Log.i(String.format("Touched at %d, %d", x, y));
        engine.addEntity(new Ball(Position.of(x, y), Dimension.of(15)));
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
}
