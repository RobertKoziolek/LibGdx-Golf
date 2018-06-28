package com.robcio.golf.entity.recipe;

import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Recipe {
    private final Position position;
    private final Dimension dimension;
    private final BallType ballType;
}
