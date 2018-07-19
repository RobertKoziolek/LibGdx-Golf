package com.robcio.golf.component.util;

import com.badlogic.ashley.core.Component;

import java.util.HashSet;
import java.util.Set;

public abstract class Group<T extends InGroup>  implements Component {
    final public Set<T> set = new HashSet<>();
}
