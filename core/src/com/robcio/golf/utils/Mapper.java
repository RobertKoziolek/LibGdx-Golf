package com.robcio.golf.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.robcio.golf.component.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {

    final public static ComponentMapper<Attracted> attracted = ComponentMapper.getFor(Attracted.class);
    final public static ComponentMapper<Box2dBody> box2dBody = ComponentMapper.getFor(Box2dBody.class);
    final public static ComponentMapper<Dimension> dimension = ComponentMapper.getFor(Dimension.class);
    final public static ComponentMapper<Force> force = ComponentMapper.getFor(Force.class);
    final public static ComponentMapper<Impulse> impulse = ComponentMapper.getFor(Impulse.class);
    final public static ComponentMapper<InBowl> inBowl = ComponentMapper.getFor(InBowl.class);
    final public static ComponentMapper<Position> position = ComponentMapper.getFor(Position.class);
    final public static ComponentMapper<Renderable> renderable = ComponentMapper.getFor(Renderable.class);

}
