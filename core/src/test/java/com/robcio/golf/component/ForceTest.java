package com.robcio.golf.component;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ForceTest {

    @Test
    public void shouldInitializeProperly() {
        final Force force = Force.of(9f);

        assertThat(force).isNotNull();
        assertThat(force.value).isEqualTo(9f);
    }
}