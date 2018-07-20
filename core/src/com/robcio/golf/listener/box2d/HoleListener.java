package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.util.ToRemove;
import com.robcio.golf.entity.util.Notification;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.BodyListener;
import com.robcio.golf.listener.EntityHolder;
import lombok.Getter;

@Getter
public class HoleListener implements BodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.HOLE};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BALL};

    private final Engine engine;

    public HoleListener(final Engine engine){
        this.engine = engine;
    }

    public void beginContact(final EntityHolder entityHolder) {
        final Entity ballEntity = entityHolder.getB();
        ballEntity.add(ToRemove.self());
        engine.addEntity(new Notification("Ball poted WOOOOAH!!"));
    }

    @Override
    public void endContact(final EntityHolder entityHolder) {
        //nothing to do here
    }
}
