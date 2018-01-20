package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;
import lombok.ToString;

@ToString
public class Impulse implements Component {
    public float x = 0.0f;
    public float y = 0.0f;

    public void clear(){
        x = 0.0f;
        y = 0.0f;
    }

}
