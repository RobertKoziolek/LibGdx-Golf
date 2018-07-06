package com.robcio.golf.map.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.robcio.golf.entity.Ball;
import com.robcio.golf.entity.Bowl;
import com.robcio.golf.entity.Bumper;
import com.robcio.golf.entity.Hole;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.TextureId;

public class EllipseEntityFactory extends EntityFactory {

    private final static String BOWL = "bowl";
    private final static String BALL = "ball";
    private final static String BUMPER = "bumper";
    private final static String HOLE = "hole";
    private final static String BALL_TYPE = "ballType";

    public Entity create(final MapObject mapObject) {
        final Ellipse ellipse = ((EllipseMapObject) mapObject).getEllipse();
        ellipse.x += ellipse.width / 2;
        ellipse.y += ellipse.height / 2;
        final String type = (String) mapObject.getProperties().get(TYPE);
        final float rotation = getRotation(mapObject);
        switch (type) {
            case BOWL:
                return new Bowl(ellipse, rotation, TextureId.BOWL);
            case BALL:
                return new Ball(ellipse, rotation, getBallType(mapObject));
            case BUMPER:
                return new Bumper(ellipse, rotation);
            case HOLE:
                return new Hole(ellipse, rotation);
            default:
                throw new IllegalArgumentException(
                        String.format("MapReader has an unknown Ellipse object type '%s'", type));
        }
    }

    private BallType getBallType(final MapObject object) {
        final Object ballTypeProperty = object.getProperties().get(BALL_TYPE);
        if (ballTypeProperty != null) {
            final String ballTypeString = ballTypeProperty.toString();
            final BallType ballType = BallType.valueOf(ballTypeString);
            if (ballType == null) throw new IllegalArgumentException("Ball type is not supported");
            return ballType;
        }
        return BallType.WHITE;
    }
}
