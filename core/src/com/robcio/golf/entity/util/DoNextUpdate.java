package com.robcio.golf.entity.util;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.util.CommandComponent;
import com.robcio.golf.utils.Command;

public class DoNextUpdate extends Entity {

    public DoNextUpdate(final Command command) {
        add(new CommandComponent(command));
    }
}