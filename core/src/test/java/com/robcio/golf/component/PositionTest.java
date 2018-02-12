package com.robcio.golf.component;

import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.utils.Maths;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PositionTest {

    @Test
    public void shouldInitializeProperly() {
        final Position position = Position.of(9f, 9f);

        assertThat(position).isNotNull();
        assertThat(position.x).isEqualTo(9f);
        assertThat(position.y).isEqualTo(9f);
    }

    @Test
    public void shouldSetWithVector2() {
        final Position position = Position.of(9f, 9f);

        position.set(Vector2.Zero.cpy());

        assertThat(position).isNotNull();
        assertThat(position.x).isEqualTo(0f);
        assertThat(position.y).isEqualTo(0f);
    }

    @Test
    public void shouldGetCorrectDistance() {
        final Position positionA = Position.of(9f, 9f);
        final Position positionB = Position.of(0f, 0f);

        final float distance = Position.distance(positionA, positionB);

        assertThat(distance).isNotZero();
    }

    @Test
    public void shouldConvertToBox2dCorrectly() {
        final Position position = Position.of(18f, 20f);
        final Position box2dPosition = Position.toBox2D(position);

        assertThat(position).isNotNull();
        assertThat(box2dPosition).isNotNull();

        assertThat(position).isNotEqualTo(box2dPosition);

        assertThat(position.x).isEqualTo(18f);
        assertThat(position.y).isEqualTo(20f);
        assertThat(box2dPosition.x).isEqualTo(18f / Maths.PPM);
        assertThat(box2dPosition.y).isEqualTo(20f / Maths.PPM);
    }
}