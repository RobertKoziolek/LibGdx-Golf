package com.robcio.golf.component.structure;

import com.robcio.golf.component.CloneableComponent;
import com.robcio.golf.utils.Maths;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Dimension implements CloneableComponent<Dimension> {
    public float width;
    public float height;

    public static Dimension of(final float width, final float height) {
        return new Dimension(width, height);
    }

    public static Dimension of(final float r) {
        return new Dimension(r, r);
    }

    public boolean isSquare() {
        return (Math.abs(width - height) < 0.005f);
    }

    public float getRadiusX() {
        return width / 2f;
    }

    public float getRadiusY() {
        return height / 2f;
    }

    public float getLower() {
        return Math.min(width, height);
    }

    public static Dimension toBox2D(final Dimension dimension) {
        return Dimension.of(dimension.width / Maths.PPM, dimension.height / Maths.PPM);
    }

    public static Dimension radiusToBox2D(final Dimension dimension) {
        return Dimension.of(dimension.getRadiusX() / Maths.PPM, dimension.getRadiusY() / Maths.PPM);
    }

    @Override
    public Dimension clone() {
        return Dimension.of(width, height);
    }
}
