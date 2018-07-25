package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.graphics.LightningInfo;
import com.robcio.golf.component.graphics.Tinted;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.entity.graphics.LightningLine;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class LightningSplitter implements EntityListener {

    final public static Family family = Family.all(LightningInfo.class)
                                              .get();

    final private Engine engine;

    @Override
    public void entityAdded(final Entity entity) {
        final LightningInfo lightningInfo = Mapper.lightningInfo.get(entity);
        final Position start = lightningInfo.source;
        final Position end = lightningInfo.destination;
        final Tinted tinted = lightningInfo.color == null ? null : Tinted.of(lightningInfo.color);

        final Position tangent = Position.sub(end, start);
        final Position normal = Position.nor(Position.of(tangent.y, -tangent.x));
        float length = Position.len(tangent);

        final float[] positions = new float[(int) (length / 5)];
        for (int i = 1; i < positions.length - 1; i++) {
            positions[i] = Maths.nextFloat();
        }
        Arrays.sort(positions);
        final float sway = 5;
        final float jaggedness = 1 / sway;

        Position prevPoint = start.clone();
        float prevDisplacement = 0;

        for (int i = 1; i < positions.length; i++) {
            float pos = positions[i];
            float scale = (Position.len(normal) * jaggedness) * (pos - positions[i - 1]);
            float envelope = pos > 0.95f ? 22 * (1 - pos) : 1;

            float displacement = Maths.random(-sway, sway);
            displacement -= (displacement - prevDisplacement) * (1 - scale);
            displacement *= envelope;

            final Position displacementVector = Position.mul(normal, displacement * length/2);


            final Position movedPosition = Position.mul(tangent, pos);
            Position tempPoint = Position.add(start, movedPosition);
            Position point = Position.add(tempPoint, displacementVector);

            addLightningLine(point, prevPoint, tinted);

            prevPoint = point;
            prevDisplacement = displacement;

        }
        addLightningLine(end.clone(), prevPoint, tinted);
        entity.add(ToRemove.self());
    }

    private void addLightningLine(final Position prevPoint, final Position point, final Tinted tinted) {
        final LightningLine lightningLine = new LightningLine(prevPoint, point);
        if (tinted != null) {
            lightningLine.add(tinted);
        }
        engine.addEntity(lightningLine);
    }

    @Override
    public void entityRemoved(final Entity entity) {
    }
}
