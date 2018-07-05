package com.robcio.golf.map.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Direction;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.*;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Log;

public class EntityFactory {

    public Entity create(final MapObject mapObject) {
        if (mapObject instanceof EllipseMapObject) {
            final Ellipse ellipse = ((EllipseMapObject) mapObject).getEllipse();
            ellipse.x += ellipse.width / 2;
            ellipse.y += ellipse.height / 2;
            final String type = (String) mapObject.getProperties().get("type");
            final float rotation = getRotation(mapObject);
            switch (type) {
                case "bowl":
                    return new Bowl(ellipse, rotation, TextureId.BOWL);
                case "ball":
                    return new Ball(ellipse, rotation, getBallType(mapObject));
                case "bumper":
                    return new Bumper(ellipse, rotation);
                case "hole":
                    return new Hole(ellipse, rotation);
                default:
                    throw new IllegalArgumentException(
                            String.format("MapReader has an unknown Ellipse object type '%s'", type));
            }
        } else if (mapObject instanceof RectangleMapObject) {
            final Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            rectangle.x += rectangle.width / 2;
            rectangle.y += rectangle.height / 2;
            final String type = (String) mapObject.getProperties().get("type");
            final float rotation = getRotation(mapObject);
            switch (type) {
                case "box":
                    return new Box(rectangle, rotation);
                case "obstacle":
                    return new Obstacle(rectangle, rotation);
                case "slope":
                    return new Slope(rectangle, rotation, getDirection(mapObject), getForce(mapObject));
                default:
                    throw new IllegalArgumentException(
                            String.format("MapReader has an unknown Rectangle object type '%s'", type));
            }
        }
        //TODO zastanowic sie nad systemem bledow, zebrane w jednym miejscu, moze jakis log
        throw new IllegalArgumentException("MapReader does not recognize map object type");
    }

    private float getRotation(final MapObject mapObject) {
        try {
            return (float) mapObject.getProperties().get("rotation");
        } catch (final NullPointerException e) {
            return 0f;
        }
    }


    private BallType getBallType(final MapObject object) {
        final Object ballTypeProperty = object.getProperties().get("ballType");
        if (ballTypeProperty != null) {
            final String ballTypeString = ballTypeProperty.toString();
            final BallType ballType = BallType.valueOf(ballTypeString);
            if (ballType == null) throw new IllegalArgumentException("Ball type is not supported");
            return ballType;
        }
        return BallType.WHITE;
    }

    private Direction getDirection(final MapObject object) {
        final Object direction = object.getProperties().get("direction");
        if (direction != null) {
            final String directionString = direction.toString();
            return Direction.of(directionString);
        }
        throw new IllegalArgumentException("Required direction property not found");
    }

    private Force getForce(final MapObject object) {
        final Object force = object.getProperties().get("force");
        if (force != null) {
            final float forceValue = (float) force;
            return Force.of(forceValue);
        }
        throw new IllegalArgumentException("Required force property not found");


    }
}
