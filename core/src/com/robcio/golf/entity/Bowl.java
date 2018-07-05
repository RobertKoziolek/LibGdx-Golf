package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyFactory;

public class Bowl extends Entity {

    public Bowl(final Position position, final Dimension dimension, final float angle, final TextureId textureId) {
        final Body body = BodyFactory
                .createCircular(position, dimension, true, true, angle, Bits.C.BALL_MANIPULANT, Bits.C.BALL);

        body.setUserData(this);
        body.getFixtureList().get(0).setSensor(true);
        flags = EntityFlags.BOWL.getId();

        add(new Selectable());
        add(position);
        add(dimension);
        add(Box2dBody.of(body));
        add(Renderable.of(textureId, 5));
    }

    public Bowl(final Ellipse ellipse, final float angle, final TextureId textureId) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height), angle, textureId);
    }
}
