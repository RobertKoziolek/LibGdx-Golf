package com.robcio.golf.component.flag;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToAdd implements Component {

    public final Class<? extends Entity> entity;

}
