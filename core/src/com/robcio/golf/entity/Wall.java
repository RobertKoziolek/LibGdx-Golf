package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.Box2dBody;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.component.Renderable;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.utils.Textures;
import com.robcio.golf.world.BodyFactory;

public class Wall extends Entity {

    public Wall(final Position position, final Dimension dimension){
        final Body body = BodyFactory.createBox(position, dimension, true, true, 1, 10);
        body.setUserData(this);
        add(new Box2dBody(body));
        add(position);
        add(dimension);
        add(Renderable.of(Textures.WALL));
    }
}
