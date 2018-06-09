package com.robcio.golf.enumeration;

import com.badlogic.ashley.core.EntitySystem;
import com.robcio.golf.system.KickToSystem;
import com.robcio.golf.system.KickingSystem;
import com.robcio.golf.system.MoveSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MouseMode {
    KICKING("Kicking", KickingSystem.class),
    KICKTO("Kick to", KickToSystem.class),
    CREATING("Creating", null),
    MOVING("Moving", MoveSystem.class);

    final private String tooltip;
    final private Class<? extends EntitySystem> systemClass;
}
