package com.robcio.golf.component;

import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.utils.Maths;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


public class DimensionTest {

    @Test
    public void shouldInitializeProperly() {
        final Dimension dimension = Dimension.of(9f, 9f);

        assertThat(dimension).isNotNull();
        assertThat(dimension.width).isEqualTo(9f);
        assertThat(dimension.height).isEqualTo(9f);
    }

    @Test
    public void shouldInitializeProperlyWithRadius() {
        final Dimension dimension = Dimension.of(9f);

        assertThat(dimension).isNotNull();
        assertThat(dimension.width).isEqualTo(9f);
        assertThat(dimension.height).isEqualTo(9f);
    }

    @Test
    public void shouldBeSquare() {
        final Dimension dimension = Dimension.of(9f);

        assertThat(dimension).isNotNull();
        assertThat(dimension.isSquare()).isTrue();
    }

    @Test
    public void shouldNotBeSquare() {
        final Dimension dimension = Dimension.of(9f, 1f);

        assertThat(dimension).isNotNull();
        assertThat(dimension.isSquare()).isFalse();
    }

    @Test
    public void shouldGetRadius() {
        final Dimension dimension = Dimension.of(18f, 20f);

        assertThat(dimension).isNotNull();
        assertThat(dimension.getRadius1()).isEqualTo(9f);
        assertThat(dimension.getRadius2()).isEqualTo(10f);
    }

    @Test
    public void shouldConvertToBox2dCorrectly() {
        final Dimension dimension = Dimension.of(18f, 20f);
        final Dimension box2dDimension = Dimension.toBox2D(dimension);

        assertThat(dimension).isNotNull();
        assertThat(box2dDimension).isNotNull();

        assertThat(dimension).isNotEqualTo(box2dDimension);

        assertThat(dimension.width).isEqualTo(18f);
        assertThat(dimension.height).isEqualTo(20f);
        assertThat(box2dDimension.width).isEqualTo(18f / Maths.PPM);
        assertThat(box2dDimension.height).isEqualTo(20f / Maths.PPM);
    }

    @Test
    public void shouldConvertToBox2dRadiusCorrectly() {
        final Dimension dimension = Dimension.of(18f, 20f);
        final Dimension box2dDimension = Dimension.radiusToBox2D(dimension);

        assertThat(dimension).isNotNull();
        assertThat(box2dDimension).isNotNull();

        assertThat(dimension).isNotEqualTo(box2dDimension);

        assertThat(dimension.width).isEqualTo(18f);
        assertThat(dimension.height).isEqualTo(20f);
        assertThat(box2dDimension.width).isEqualTo(9f / Maths.PPM);
        assertThat(box2dDimension.height).isEqualTo(10f / Maths.PPM);
    }
}