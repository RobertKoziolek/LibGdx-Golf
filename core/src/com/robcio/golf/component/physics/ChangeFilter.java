package com.robcio.golf.component.physics;

import com.badlogic.ashley.core.Component;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangeFilter implements Component {
    public Short categoryBits;
    public Short maskBits;

    public static ChangeFilter noCategory() {
        return new ChangeFilter((short) 0, null);
    }

    public static ChangeFilter ofCategory(final short categoryBits){
        return new ChangeFilter(categoryBits, null);
    }

    public static ChangeFilter of(final Short categoryBits, final Short maskBits){
        return new ChangeFilter(categoryBits, maskBits);
    }

}
