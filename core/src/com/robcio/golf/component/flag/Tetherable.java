package com.robcio.golf.component.flag;

import com.badlogic.ashley.core.Component;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class Tetherable implements Component {
    final public Set<Tether> tethers = new HashSet<>();
}
