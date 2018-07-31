package com.robcio.golf.component;

import com.badlogic.ashley.core.Component;

public interface CloneableComponent extends Component {
    CloneableComponent clone();
}
