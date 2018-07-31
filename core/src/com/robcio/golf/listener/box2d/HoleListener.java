package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.graphics.FadeOut;
import com.robcio.golf.component.physics.ChangeFilter;
import com.robcio.golf.component.structure.HardImpulse;
import com.robcio.golf.entity.util.Notification;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.EntityHolder;
import lombok.Getter;

@Getter
public class HoleListener extends EngineBodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.HOLE};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BALL};

    public HoleListener(final Engine engine) {
        super(engine);
    }

    public void beginContact(final EntityHolder entityHolder) {
        final Entity ballEntity = entityHolder.getB();
        ballEntity.add(ChangeFilter.noCategory());
        ballEntity.add(HardImpulse.stop());
        ballEntity.add(FadeOut.of(0.15f));
        //TODO kiedy bedzie jakis log, wysylac moznaby tez obiekt (entity, teksturke czy cos) zeby jego info mozna bylo odczytac
        engine.addEntity(new Notification("Ball poted WOOOOAH!!"));
    }

    @Override
    public void endContact(final EntityHolder entityHolder) {
        //nothing to do here
    }
}
