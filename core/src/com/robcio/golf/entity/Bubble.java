package com.robcio.golf.entity;

import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.flag.ToRemove;
import com.robcio.golf.component.structure.*;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyFactory;

//TODO dwa systemy ruchu pilki/pilek - naciaganie jak golf/proca, oraz do celu - klikam dokad ma poleciec
public class Bubble extends Ball {

    private Bubble(final Position position, final Dimension dimension) {
        super(position, dimension, null);
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
