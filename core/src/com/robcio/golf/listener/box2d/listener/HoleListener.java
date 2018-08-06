package com.robcio.golf.listener.box2d.listener;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.graphics.FadeOut;
import com.robcio.golf.component.physics.ChangeFilter;
import com.robcio.golf.component.structure.HardImpulse;
import com.robcio.golf.entity.util.Notification;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.box2d.holder.ContactInfoHolder;
import com.robcio.golf.listener.box2d.EngineBodyListener;
import lombok.Getter;

@Getter
public class HoleListener extends EngineBodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.HOLE};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BALL};

    public HoleListener(final Engine engine) {
        super(engine);
    }

    public void beginContact(final ContactInfoHolder contactInfoHolder) {
        final Entity ballEntity = contactInfoHolder.getB();
        ballEntity.add(ChangeFilter.noCategory());
        ballEntity.add(HardImpulse.stop());
        ballEntity.add(FadeOut.of(0.15f));
        //TODO kiedy bedzie jakis log, wysylac moznaby tez obiekt (entity, teksturke czy cos) zeby jego info mozna bylo odczytac
        addEntity(new Notification("Ball poted WOOOOAH!!"));
    }

    @Override
    public void endContact(final ContactInfoHolder contactInfoHolder) {
        //nothing to do here
    }
}
