package com.robcio.golf.entity;

import com.badlogic.gdx.math.Ellipse;
import com.robcio.golf.component.graphics.Renderable;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.Timer;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.enumeration.TextureId;

public class Bubble extends Ball {

    private Bubble(final Position position, final Dimension dimension) {
        super(position, dimension, 0f, null);
        add(Renderable.of(TextureId.BUBBLE, 20));
        add(Timer.of(0.3f, ToRemove.self()));

    }

    public Bubble(final Ellipse ellipse) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height));
    }

    public static Bubble of(final Recipe recipe) {
        return new Bubble(recipe.getPosition().clone(), recipe.getDimension().clone());
    }
}
