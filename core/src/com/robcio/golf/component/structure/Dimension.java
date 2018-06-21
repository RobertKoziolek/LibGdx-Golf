package com.robcio.golf.component.structure;

import com.badlogic.ashley.core.Component;
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

    //TODO moze nie radiusONE tylko cos innego
    public float getRadius1() {
        return width / 2f;
    }

    public float getRadius2() {
        return height / 2f;
    }

    public static Dimension toBox2D(final Dimension dimension) {
        return Dimension.of(dimension.width / Maths.PPM, dimension.height / Maths.PPM);
    }

    public static Dimension radiusToBox2D(final Dimension dimension) {
        return Dimension.of(dimension.getRadius1() / Maths.PPM, dimension.getRadius2() / Maths.PPM);
    }

    @Override
    public Dimension clone() {
        return Dimension.of(width, height);
    }
}
