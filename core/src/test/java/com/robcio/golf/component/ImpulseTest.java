package com.robcio.golf.component;

import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.structure.Impulse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ImpulseTest {

    @Test
    public void shouldThrowWhenInitializedWithNull() {
        try {
            new Impulse(null);
            fail("should have thrown exception");
        } catch (final IllegalArgumentException e) {
            assertThat(e).hasMessage("Vector2 impulse cannot be null");
        }
    }

    @Test
    public void shouldInitializeProperly() {
        final Impulse impulse = new Impulse(Vector2.Zero.cpy());

        assertThat(impulse).isNotNull();
        assertThat(impulse.impulse.x).isEqualTo(0f);
        assertThat(impulse.impulse.y).isEqualTo(0f);
    }
}