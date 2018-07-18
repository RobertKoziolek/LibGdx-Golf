package com.robcio.golf.component.util;

import com.badlogic.ashley.core.Component;
import com.robcio.golf.MainClass;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotificationData implements Component {
    public String text;
    public float x, y;

    public NotificationData(final String text) {
        this.text = text;
        this.x = MainClass.WIDTH / 4;
        this.y = MainClass.HEIGHT - 80;
    }
}
