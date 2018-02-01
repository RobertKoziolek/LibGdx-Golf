package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MouseMode {
    CREATING("Creating"), MOVING("Moving"), KICKING("Kicking");

    final private String tooltip;

}
