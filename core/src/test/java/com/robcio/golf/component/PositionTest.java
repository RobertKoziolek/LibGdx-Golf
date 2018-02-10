package com.robcio.golf.component;

import com.badlogic.gdx.math.Vector2;
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
}