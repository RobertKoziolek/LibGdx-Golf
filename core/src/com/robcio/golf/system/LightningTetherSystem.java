package com.robcio.golf.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.robcio.golf.component.flag.Tether;
import com.robcio.golf.component.flag.Tetherable;
import com.robcio.golf.component.graphics.Tinted;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.entity.graphics.Lightning;
import com.robcio.golf.utils.Mapper;
import lombok.Getter;

public class LightningTetherSystem extends GroupedIteratingSystem {
    @Getter
    private final ComponentMapper groupMapper = Mapper.tetherable;

    private final World world;

    private float time;

    public LightningTetherSystem(final int priority, final World world) {
        super(Family.all(Tetherable.class)
                    .get(), priority);
        this.world = world;
    }

    @Override
    public void update(final float deltaTime) {
        if ((time += deltaTime) > 0.07f) {
            super.update(deltaTime);
            time = 0f;
        }
    }

    @Override
    protected void processEntity(final Entity entity, final InGroup inGroup) {
        addLightning(entity, (Tether) inGroup);
    }

    private void addLightning(final Entity entity, final Tether tether) {
        if (!assertNoObstaclesBlocking(tether)) {
            return;
        }
        Color color = Color.WHITE;
        final Tinted tinted = Mapper.tinted.get(entity);
        if (tinted != null) {
            color = tinted.color;
        }

        getEngine().addEntity(Lightning.of(tether.position1, tether.position2, color));
    }

    private boolean assertNoObstaclesBlocking(final Tether tether) {
        final Vector2 position1 = Position.toBox2D(tether.position1)
                                          .getVector2();
        final Vector2 position2 = Position.toBox2D(tether.position2)
                                          .getVector2();
        final boolean[] tibia = new boolean[1];
        world.rayCast(new RayCastCallback() {
            int i;

            @Override
            public float reportRayFixture(final Fixture fixture,
                                          final Vector2 point,
                                          final Vector2 normal,
                                          final float fraction) {
                tibia[0] = point.cpy()
                                .sub(position2)
                                .len() < 0.5f;
                return fraction;
            }
        }, position1, position2);
        return tibia[0];
    }
}
