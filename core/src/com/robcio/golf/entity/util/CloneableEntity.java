package com.robcio.golf.entity.util;

import com.badlogic.ashley.core.Entity;

public abstract class CloneableEntity extends Entity {
    public abstract Entity clone();
}
