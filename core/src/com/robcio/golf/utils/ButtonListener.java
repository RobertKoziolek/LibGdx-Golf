package com.robcio.golf.utils;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import lombok.AllArgsConstructor;

import java.util.Observable;
import java.util.Observer;

@AllArgsConstructor
public class ButtonListener implements Observer {

    private final TextButton button;

    @Override
    public void update(final Observable o, final Object arg) {
        if (arg instanceof String){
            button.setText(String.valueOf(arg));
        }
    }
}
