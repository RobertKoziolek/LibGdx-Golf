package com.robcio.golf.component.flag;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.component.util.InGroup;

public class Tether extends InGroup {
    final public Position position1, position2;

    public Tether(final Position position1, final Position position2, final Entity groupedBy) {
        super(groupedBy);
        this.position1 = position1;
        this.position2 = position2;
    }
}
