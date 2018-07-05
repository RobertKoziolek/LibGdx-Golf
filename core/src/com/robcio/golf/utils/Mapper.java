package com.robcio.golf.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.robcio.golf.component.flag.*;
import com.robcio.golf.component.structure.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {

    final public static ComponentMapper<Selected> selected = ComponentMapper.getFor(Selected.class);
    final public static ComponentMapper<Trailing> trailing = ComponentMapper.getFor(Trailing.class);
    final public static ComponentMapper<Box2dBody> box2dBody = ComponentMapper.getFor(Box2dBody.class);
    final public static ComponentMapper<Dimension> dimension = ComponentMapper.getFor(Dimension.class);
    final public static ComponentMapper<Position> position = ComponentMapper.getFor(Position.class);
    final public static ComponentMapper<Direction> direction = ComponentMapper.getFor(Direction.class);
    final public static ComponentMapper<Line> line = ComponentMapper.getFor(Line.class);
    final public static ComponentMapper<Force> force = ComponentMapper.getFor(Force.class);
    final public static ComponentMapper<Impulse> impulse = ComponentMapper.getFor(Impulse.class);
    final public static ComponentMapper<InBowl> inBowl = ComponentMapper.getFor(InBowl.class);
    final public static ComponentMapper<OnSlope> onSlope = ComponentMapper.getFor(OnSlope.class);
    final public static ComponentMapper<Dispensing> dispensing = ComponentMapper.getFor(Dispensing.class);
    final public static ComponentMapper<Renderable> renderable = ComponentMapper.getFor(Renderable.class);
    final public static ComponentMapper<Tinted> tinted = ComponentMapper.getFor(Tinted.class);
    final public static ComponentMapper<ToRemove> toRemove = ComponentMapper.getFor(ToRemove.class);
    final public static ComponentMapper<Timer> timer = ComponentMapper.getFor(Timer.class);
    final public static ComponentMapper<RepeatingTimer> repeatingTimer = ComponentMapper.getFor(RepeatingTimer.class);
}
