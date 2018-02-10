package com.robcio.golf.component;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class Box2dBodyTest {

    @Test
    public void shouldThrowWhenInitializedWithNull() {
        try {
            new Box2dBody(null);
            fail("should have thrown exception");
        } catch (final IllegalArgumentException e) {
            assertThat(e).hasMessage("Body cannot be null");
        }
    }
}