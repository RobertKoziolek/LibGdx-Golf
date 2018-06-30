package com.robcio.golf.entity.recipe;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.entity.Bubble;


public class EntityFactory {

    public static Entity createFrom(final Recipe recipe) {
        return Bubble.of(recipe);
    }
}
