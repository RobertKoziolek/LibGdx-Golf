package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.flag.*;
import com.robcio.golf.component.structure.*;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyFactory;

public class Dispenser extends Entity {

    public Dispenser(final Position position, final Dimension dimension, final Force force) {
        final Body body = BodyFactory
                .createCircular(position, dimension, false, false, Bits.C.FREE_OBJECT, Bits.M.FREE_OBJECT_WILL_HIT);
        body.setUserData(this);
        body.getFixtureList().get(0).setDensity(55.3f);
        body.setLinearDamping(4f);
        body.setAngularDamping(4f);
        flags = EntityFlags.BALL.getId();

        add(new Selectable());
        add(Box2dBody.of(body));
        add(position);
        add(dimension);
        add(force);
        add(Renderable.of(TextureId.STAR));
        add(Timer.of(1f, ToRemove.self()));

        //TODO te chamskie klonowanie tworzy wpierw balla, ktorego body sie nie usuwa z box2d world
        //TODO moze zamiast chamsko klonowac zrobic interfejs typu Recipe ktory by okreslal jak tworzyc obiekty, lekko jak fabryka
        add(new Dispensing(new Recipe(position, dimension, BallType.WHITE)));
    }

    public Dispenser(final Rectangle rectangle, final Force force) {
        this(Position.of(rectangle.x, rectangle.y), Dimension.of(rectangle.width, rectangle.height), force);
    }
}
