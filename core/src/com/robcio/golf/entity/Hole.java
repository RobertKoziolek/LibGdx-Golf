package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyFactory;

//TODO dodac jakis vortex czy cos, zabawa z fizyka
public class Hole extends Entity {

    public Hole(final Position position, final Dimension dimension) {
        final Body body = BodyFactory
                .createCircular(position, Dimension.of(1f), true, true, Bits.C.BALL_MANIPULANT, Bits.C.BALL);

        body.setUserData(this);
        flags = EntityFlags.HOLE.getId();

        add(position);
        add(dimension);
        add(new Box2dBody(body));
        //TODO zmienic nazwy sensownie
        add(new com.robcio.golf.component.flag.Hole());
    }

    public Hole(final Ellipse ellipse) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height));
    }
}
