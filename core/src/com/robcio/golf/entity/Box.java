package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyFactory;

public class Box extends Entity {

    public Box(final Position position, final Dimension dimension) {
        final Body body = BodyFactory
                .createBox(position, dimension, false, false, Bits.C.FREE_OBJECT, Bits.M.FREE_OBJECT_WILL_HIT);
        body.setUserData(this);
        body.getFixtureList().get(0).setDensity(2f);
        body.setLinearDamping(0.99f);
        body.setAngularDamping(0.99f);
        add(new Box2dBody(body));
        add(position);
        add(dimension);
        add(Renderable.of(TextureId.BOX));
        add(new Kickable());
    }

    public Box(final Rectangle rectangle) {
        this(Position.of(rectangle.x, rectangle.y), Dimension.of(rectangle.width, rectangle.height));
    }
}
