package com.robcio.golf.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Position;

//TODO w zasadzie to jest niepotrzebne do niczego co narazie i nie widze w przyszlosci sensu
public class WallSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<Position> pm = ComponentMapper.getFor(Position.class);
    private ComponentMapper<Dimension> dm = ComponentMapper.getFor(Dimension.class);

    public WallSystem() {}

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(Position.class, Dimension.class).get());
    }

    public void update(float deltaTime) {
    }
}
