package com.robcio.golf.entity;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.util.CommandComponent;
import com.robcio.golf.utils.Command;

public class Do extends Entity {

    public Do(final Command command) {
        add(new CommandComponent(command));
    }
}