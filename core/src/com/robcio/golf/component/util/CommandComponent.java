package com.robcio.golf.component.util;

import com.badlogic.ashley.core.Component;
import com.robcio.golf.utils.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandComponent implements Component {

    public final Command command;

}
