package com.robcio.golf.listener;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.util.Group;
import com.robcio.golf.component.util.InGroup;
import com.robcio.golf.enumeration.EntityFlags;

import java.util.Map;

public abstract class GroupedBodyListener implements BodyListener {

    protected abstract ComponentMapper<? extends Group> getGroupMapper();

    protected abstract InGroup create(final Map<Integer, Body> map);

    @Override
    public void beginContact(final Map<Integer, Body> map) {
        final Group group = getGroupMapper().get(getEntityB(map));
        if (group != null) {
            group.set.add(create(map));
        }
    }

    @Override
    public void endContact(final Map<Integer, Body> map) {
        final Group<? extends InGroup>  group = getGroupMapper().get(getEntityB(map));
        if (group != null) {
            final Entity entityA = getEntityA(map);
            for (final InGroup inGroup: group.set) {
                if(inGroup.groupedBy == entityA){
                    group.set.remove(inGroup);
                    break;
                }
            }
        }
    }

    protected Body getBodyA(final Map<Integer, Body> map) {
        return getBody(map, getEntityFlagsA());
    }

    protected Body getBodyB(final Map<Integer, Body> map) {
        return getBody(map, getEntityFlagsB());
    }

    protected Entity getEntityA(final Map<Integer, Body> map) {
        return getEntity(map, getEntityFlagsA());
    }

    protected Entity getEntityB(final Map<Integer, Body> map) {
        return getEntity(map, getEntityFlagsB());
    }

    private Entity getEntity(final Map<Integer, Body> map, final EntityFlags entityFlags) {
        final Body body = getBody(map, entityFlags);
        return (Entity) body.getUserData();
    }

    private Body getBody(Map<Integer, Body> map, EntityFlags entityFlags) {
        return map.get(entityFlags.getId());
    }

}
