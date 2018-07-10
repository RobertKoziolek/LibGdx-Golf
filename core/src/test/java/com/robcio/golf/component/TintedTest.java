package com.robcio.golf.component;

import com.badlogic.gdx.graphics.Color;
import com.robcio.golf.component.graphics.Tinted;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TintedTest {

    @Test
    public void shouldThrowWhenInitializedWithNullDimension() {
        try {
            Tinted.of(null);
            fail("should have thrown exception");
        } catch (final IllegalArgumentException e) {
            assertThat(e).hasMessage("Color cannot be null");
        }
    }

    @Test
    public void shouldInitializeProperly() {
        final Tinted tinted = Tinted.of(Color.GREEN);

        assertThat(tinted).isNotNull();
        assertThat(tinted.color).isEqualTo(Color.GREEN);
    }
}