package com.robcio.golf.listener.input;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.robcio.golf.component.flag.Selected;
import com.robcio.golf.component.structure.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointerPosition {

    private int x, y;

    private final Camera camera;

    public PointerPosition(final Camera camera) {
        this.camera = camera;
    }

    public Position getUnprojectedPosition() {
        final Vector3 realCoords = camera.unproject(new Vector3(x, y, 0f));
        return Position.of(realCoords.x, realCoords.y);
    }

    public void updateSelectionPoint() {
        Selected.position = Position.toBox2D(getUnprojectedPosition());
    }

}
