package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyFactory;

public class Bumper extends Entity {

    public Bumper(final Position position, final Dimension dimension) {
        final Body body = BodyFactory
                .createCircular(position, dimension, true, true, Bits.C.BALL_MANIPULANT, Bits.M.FREE_OBJECT_WILL_HIT);

        body.setUserData(this);
        flags = EntityFlags.BUMPER.getId();

        add(position);
        add(dimension);
        add(Force.of(55));
        add(new Box2dBody(body));
        add(Renderable.of(TextureId.BUMPER));
    }

    public Bumper(final Ellipse ellipse) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height));
    }
}
