package com.robcio.golf.enumeration;

import com.badlogic.ashley.core.EntitySystem;
import com.robcio.golf.system.KickingSystem;
import com.robcio.golf.system.SelectionSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MouseMode {
    CREATING("Creating", null), MOVING("Moving", SelectionSystem.class), KICKING("Kicking", KickingSystem.class);

    final private String tooltip;
    final private Class<? extends EntitySystem> systemClass;
}