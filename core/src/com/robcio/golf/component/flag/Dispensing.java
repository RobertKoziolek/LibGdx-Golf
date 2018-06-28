package com.robcio.golf.component.flag;

import com.badlogic.ashley.core.Component;
import com.robcio.golf.entity.recipe.Recipe;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Dispensing implements Component {

    public final Recipe recipe;

}
