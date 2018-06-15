package com.robcio.golf.component;

import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.flag.InBowl;
import com.robcio.golf.component.structure.Dimension;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class InBowlTest {

    @Test
    public void shouldThrowWhenInitializedWithNullVector() {
        try {
            new InBowl(null, Dimension.of(9f));
            fail("should have thrown exception");
        } catch (final IllegalArgumentException e) {
            assertThat(e).hasMessage("Vector2 bowlCenter cannot be null");
        }
    }

    @Test
    public void shouldThrowWhenInitializedWithNullDimension() {
        try {
            new InBowl(Vector2.Zero.cpy(), null);
            fail("should have thrown exception");
        } catch (final IllegalArgumentException e) {
            assertThat(e).hasMessage("Dimension bowlDimension cannot be null");
        }
    }

    @Test
    public void shouldInitializeProperly() {
        final InBowl inBowl = new InBowl(Vector2.Zero.cpy(), Dimension.of(9f));

        assertThat(inBowl).isNotNull();
        assertThat(inBowl.bowlCenter.x).isEqualTo(0f);
        assertThat(inBowl.bowlCenter.y).isEqualTo(0f);
        assertThat(inBowl.bowlDimension.isSquare()).isTrue();
        assertThat(inBowl.bowlDimension.width).isEqualTo(9f);
    }
}