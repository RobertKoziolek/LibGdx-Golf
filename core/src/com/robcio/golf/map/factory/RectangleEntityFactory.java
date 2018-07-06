package com.robcio.golf.map.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.robcio.golf.component.structure.Direction;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.entity.*;

public class RectangleEntityFactory extends EntityFactory {

    public Entity create(final MapObject mapObject) {
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
