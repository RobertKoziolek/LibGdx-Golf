package com.robcio.golf.component;

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
}