package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.util.NotificationData;
import com.robcio.golf.component.util.Timer;
import com.robcio.golf.component.util.ToRemove;

public class Notification extends Entity {

    public Notification(final String text) {
        add(new NotificationData(text));
        add(Timer.of(3f, ToRemove.self()));
    }
}
