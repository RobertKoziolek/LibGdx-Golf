package com.robcio.golf.listener.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.robcio.golf.component.*;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.MouseMode;
import com.robcio.golf.system.ImpulseSystem;
import com.robcio.golf.system.KickingSystem;
import com.robcio.golf.system.SelectionSystem;
import com.robcio.golf.utils.Log;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static com.robcio.golf.enumeration.MouseMode.MOVING;

public class GameInputCatcher implements InputProcessor {

    private final Camera camera;

    private final Engine engine;

    private ArrayList<MouseMode> mouseModes;

    public GameInputCatcher(final Camera camera, final Engine engine) {
        this.camera = camera;
        this.engine = engine;
        mouseModes = new ArrayList<>();
        Collections.addAll(mouseModes, MouseMode.values());
        changeMouseSystemProcessing(getCurrentMouseMode(), true);
    }

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
        final Position unprojectedPosition = getUnprojectedPosition(screenX, screenY);
        //TODO tu raczej dodac jakis state pattern, zwlaszcza jesli ma tego byc wiecej
        switch (getCurrentMouseMode()) {
            case CREATING:
                engine.addEntity(new Ball(unprojectedPosition, Dimension.of(30), BallType.WHITE));
                return true;
            case MOVING:
                final Family moveFamily = Family.all(Position.class).exclude(Selected.class).get();
                if (select(screenX, screenY, unprojectedPosition, moveFamily)) return true;
                break;
            case KICKING:
                final Family kickFamily = Family.all(Position.class, Kickable.class).exclude(Selected.class).get();
                if (select(screenX, screenY, unprojectedPosition, kickFamily)) return true;
                break;
            default:
                //nothing to do here
        }
        return false;
    }

    private boolean select(final int screenX, final int screenY, final Position unprojectedPosition,
                           final Family family) {
        final ImmutableArray<Entity> moveEntities = engine
                .getEntitiesFor(family);
        for (final Entity entity : moveEntities) {
            final Position position = Mapper.position.get(entity);
            if (Position.distance(unprojectedPosition, position) < 30f) {
                setSelectionPoint(screenX, screenY);
                entity.add(new Selected());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        switch (getCurrentMouseMode()) {
            case KICKING:
                engine.getSystem(ImpulseSystem.class).update(9f);
            case MOVING:
                final ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(Selected.class).get());
                for (final Entity entity : entities) {
                    entity.remove(Selected.class);
                }
                return true;
            default:
                //nothing to do here
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        switch (getCurrentMouseMode()) {
            case KICKING:
            case MOVING:
                setSelectionPoint(screenX, screenY);
                return true;
            default:
                //nothing to do here
        }
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

    private Position getUnprojectedPosition(final int screenX, final int screenY) {
        final Vector3 realCoords = camera.unproject(new Vector3(screenX, screenY, 0f));
        return Position.of(realCoords.x, realCoords.y);
    }

    public String changeMouseMode() {
        changeMouseSystemProcessing(getCurrentMouseMode(), false);
        final MouseMode last = mouseModes.get(mouseModes.size() - 1);
        mouseModes.remove(last);
        mouseModes.add(0, last);
        changeMouseSystemProcessing(last, true);
        Log.i("Mouse mode", last.getTooltip());
        return last.getTooltip();
    }

    private void changeMouseSystemProcessing(final MouseMode currentMouseMode, final boolean processing) {
        final Class<? extends EntitySystem> systemClass = currentMouseMode.getSystemClass();
        if (systemClass != null) {
            final EntitySystem system = engine.getSystem(systemClass);
            system.setProcessing(processing);
        }
    }

    private void setSelectionPoint(int screenX, int screenY) {
        final Position position = getUnprojectedPosition(screenX, screenY);
        Selected.position = Position.toBox2D(position);
    }

    public MouseMode getCurrentMouseMode() {
        return mouseModes.get(0);
    }
}
