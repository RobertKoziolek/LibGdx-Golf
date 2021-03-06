package com.robcio.golf.entity.util;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.NotificationData;
import com.robcio.golf.component.util.Timer;
import com.robcio.golf.component.util.ToRemove;

public class Notification extends Entity {

    public Notification(final String text) {
        add(new NotificationData(text));
        add(Timer.of(3f, ToRemove.self()));
    }

    public Notification(final String text, final Position position) {
        add(new NotificationData(text, position.x, position.y));
        add(Timer.of(0.5f, ToRemove.self()));
    }
}
