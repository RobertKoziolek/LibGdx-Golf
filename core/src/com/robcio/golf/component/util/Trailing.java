package com.robcio.golf.component.util;

import com.badlogic.ashley.core.Component;
import com.robcio.golf.entity.recipe.Recipe;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Trailing implements Component {

    public int counter;

    public final Recipe recipe;

    public final float triggerSpeed;

    public static Trailing of(final Recipe recipe, final float triggerSpeed) {
        return new Trailing(0, recipe, triggerSpeed);
    }
}
