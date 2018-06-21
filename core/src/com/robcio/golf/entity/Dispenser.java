package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.Dispensing;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyFactory;

public class Dispenser extends Entity {

    public Dispenser(final Position position, final Dimension dimension, final Force force) {
        final Body body = BodyFactory
                .createBox(position, dimension, false, false, Bits.C.FREE_OBJECT, Bits.M.FREE_OBJECT_WILL_HIT);
        body.setUserData(this);
        body.getFixtureList().get(0).setDensity(0.3f);
        body.setLinearDamping(4f);
        body.setAngularDamping(4f);

        add(new Selectable());
        add(new Box2dBody(body));
        add(position);
        add(dimension);
        add(force);
        add(Renderable.of(TextureId.BOX));

        //TODO moze zamiast chamsko klonowac zrobic interfejs typu Recipe ktory by okreslal jak tworzyc obiekty, lekko jak fabryka
        add(new Dispensing(new Ball(position, dimension, BallType.GREEN)));
    }

    public Dispenser(final Rectangle rectangle, final Force force) {
        this(Position.of(rectangle.x, rectangle.y), Dimension.of(rectangle.width, rectangle.height), force);
    }
}
