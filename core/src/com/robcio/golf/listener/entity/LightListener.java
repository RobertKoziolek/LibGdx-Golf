package com.robcio.golf.listener.entity;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Color;
import com.robcio.golf.component.light.Light;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.utils.Mapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LightListener implements EntityListener {

    final public static Family family = Family.all(Light.class, Position.class, Force.class)
                                              .get();

    final private RayHandler rayHandler;

    @Override
    public void entityAdded(final Entity entity) {
//        final ConeLight coneLight = new ConeLight(rayHandler, 333, new Color(1f, 1f, 1f, 0.5f), 15, 3, 4, 0, 40f);

        final Position position = Position.toBox2D(Mapper.position.get(entity));
        final Force force = Mapper.force.get(entity);
        final PointLight pointLight = new PointLight(rayHandler, 215, new Color(0.2f, 0.2f, 0.2f, 1f), force.value,
                                                     position.x,
                                                     position.y);
        Mapper.light.get(entity).pointLight = pointLight;
    }

    @Override
    public void entityRemoved(final Entity entity) {
        Mapper.light.get(entity).pointLight.remove();
    }
}
