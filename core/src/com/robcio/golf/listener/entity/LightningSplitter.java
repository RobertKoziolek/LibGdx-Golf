package com.robcio.golf.listener.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.robcio.golf.component.graphics.LightningInfo;
import com.robcio.golf.component.structure.Position;
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
    public void entityAdded(Entity entity) {
        final LightningInfo line = Mapper.lightningInfo.get(entity);
        final Position start = line.source;
        final Position end = line.destination;

        final Position tangent = Position.sub(end, start);
        final Position normal = Position.nor(Position.of(tangent.y, -tangent.x));
        float length = Position.len(tangent);

        final float[] positions = new float[(int) (length/9)];
        for (int i = 1; i < positions.length-1; i++) {
            positions[i] = Maths.nextFloat();
        }
        Arrays.sort(positions);
        final float sway = 5;
        final float jaggedness = 1 / sway;

        Position prevPoint  = start.clone();
        float prevDisplacement = 0;


        //TODO bliskie maja za duzy rozrzut
        for (int i = 1; i < positions.length; i++) {
            float pos = positions[i];

            float scale = (Position.len(normal) * jaggedness) * (pos - positions[i - 1]);
            float envelope = pos > 0.95f ? 22 * (1 - pos) : 1;

            float displacement = Maths.random(-sway, sway);
            displacement -= (displacement - prevDisplacement) * (1 - scale);
            displacement *= envelope;

            final Position displacementVector = Position.mul(normal, displacement*255 );


            final Position movedPosition = Position.mul(tangent, pos);
            Position tempPoint = Position.add(start, movedPosition);
            Position point = Position.add(tempPoint, displacementVector);

            engine.addEntity(new LightningLine(prevPoint, point));

            prevPoint = point;
            prevDisplacement = displacement;

        }
        engine.addEntity(new LightningLine(prevPoint, end));
    }

    @Override
    public void entityRemoved(Entity entity) {
    }
}
