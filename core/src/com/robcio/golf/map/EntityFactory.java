package com.robcio.golf.map;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.*;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.TextureId;

public class EntityFactory {

    public Entity create(final MapObject object) {
        if (object instanceof EllipseMapObject) {
            final Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
            ellipse.x += ellipse.width / 2;
            ellipse.y += ellipse.height / 2;
            final String type = (String) object.getProperties().get("type");
            switch (type) {
                case "bowl":
                    return new Bowl(ellipse, TextureId.BOWL);
                case "ball":
                    return new Ball(ellipse, getBallType(object));
                case "bumper":
                    return new Bumper(ellipse);
                case "hole":
                    return new Bowl(ellipse, TextureId.HOLE);
                    //TODO wypadaloby cos ogarnac zeby jednak dwa obiekty sie pojawialy
//                return new Hole(Position.of(ellipse.x, ellipse.y), Dimension.of(1f));
                default:
                    throw new IllegalArgumentException(
                            String.format("MapReader has an unknown Ellipse object type '%s'", type));
            }
        } else if (object instanceof RectangleMapObject) {
            final Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            rectangle.x += rectangle.width / 2;
            rectangle.y += rectangle.height / 2;
            final String type = (String) object.getProperties().get("type");
            switch (type) {
                case "box":
                    return new Box(rectangle);
                case "obstacle":
                    return new Obstacle(rectangle);
                default:
                    throw new IllegalArgumentException(
                            String.format("MapReader has an unknown Rectangle object type '%s'", type));
            }
        }
        throw new IllegalArgumentException("MapReader does not recognize map object type");
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
}
