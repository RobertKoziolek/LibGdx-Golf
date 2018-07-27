package com.robcio.golf.component.physics;

import com.badlogic.ashley.core.Component;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

//TODO w miare potrzeby do rozwiniecia tu i system
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangeFilter implements Component {
    public short categoryBits;

    public static ChangeFilter noCategory() {
        return new ChangeFilter((short) 0);
    }

}
