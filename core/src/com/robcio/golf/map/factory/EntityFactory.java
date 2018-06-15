package com.robcio.golf.map.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.*;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.TextureId;

public class EntityFactory {

    public Entity create(final MapObject mapObject) {
        if (mapObject instanceof EllipseMapObject) {
            final Ellipse ellipse = ((EllipseMapObject) mapObject).getEllipse();
            ellipse.x += ellipse.width / 2;
            ellipse.y += ellipse.height / 2;
            final String type = (String) mapObject.getProperties().get("type");
            switch (type) {
                case "bowl":
                    return new Bowl(ellipse, TextureId.BOWL);
                case "ball":
                    return new Ball(ellipse, getBallType(mapObject));
                case "bumper":
                    return new Bumper(ellipse);
                case "hole":
                    return new Hole(ellipse);
                default:
                    throw new IllegalArgumentException(
                            String.format("MapReader has an unknown Ellipse object type '%s'", type));
            }
        } else if (mapObject instanceof RectangleMapObject) {
            final Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            rectangle.x += rectangle.width / 2;
            rectangle.y += rectangle.height / 2;
            final String type = (String) mapObject.getProperties().get("type");
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
        //TODO zastanowic sie nad systemem bledow, zebrane w jednym miejscu, moze jakis log
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
