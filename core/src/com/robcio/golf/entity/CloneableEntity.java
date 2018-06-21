package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;

public abstract class CloneableEntity extends Entity {
    public abstract Entity clone();
}
