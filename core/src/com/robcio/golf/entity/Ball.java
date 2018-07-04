package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.robcio.golf.component.flag.Kickable;
import com.robcio.golf.component.flag.Renderable;
import com.robcio.golf.component.flag.Selectable;
import com.robcio.golf.component.flag.Trailing;
import com.robcio.golf.component.structure.Box2dBody;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.structure.Tinted;
import com.robcio.golf.entity.recipe.Recipe;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.world.BodyFactory;

//TODO dwa systemy ruchu pilki/pilek - naciaganie jak golf/proca, oraz do celu - klikam dokad ma poleciec
public class Ball extends Entity {

    public Ball(final Position position, final Dimension dimension, final BallType ballType) {

        final Body body = BodyFactory
                .createCircular(position, dimension, false, false, Bits.C.BALL, Bits.M.BALL_WILL_HIT);
        body.setUserData(this);
        flags = EntityFlags.BALL.getId();

        final Fixture fixture = body.getFixtureList().get(0);
        fixture.setRestitution(0.5f);
        fixture.setDensity(0.4f);

        //TODO bardzo potrzebny damping predkosci, plywaja po stole
        //TODO moze na mape inny damping? generalnie powierzchnie dodac
        body.setLinearDamping(0.3f);
        body.setAngularDamping(0.3f);

        add(new Selectable());
        add(position);
        add(dimension);
        add(Box2dBody.of(body));
        add(Renderable.of(TextureId.GOLFBALL, 20));

        if (ballType != null) {
            add(Trailing.of(new Recipe(position, Dimension.of(dimension.getRadius1()), BallType.WHITE), 6f));
            if (ballType == BallType.WHITE) {
                add(new Kickable());
            } else {
                add(Tinted.of(ballType.getColor()));
            }
        }

    }

    public Ball(final Ellipse ellipse, final BallType ballType) {
        this(Position.of(ellipse.x, ellipse.y), Dimension.of(ellipse.width, ellipse.height), ballType);
    }

    public static Ball of(final Recipe recipe) {
        return new Ball(recipe.getPosition().clone(), recipe.getDimension().clone(), recipe.getBallType());
    }
}
