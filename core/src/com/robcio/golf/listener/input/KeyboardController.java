package com.robcio.golf.listener.input;

public class KeyboardController {

    public void update(final float deltaTime) {
    }

    public boolean keyDown(final int keycode) {
        //TODO registar z obserwatorem moze na mapie dla klawiszy, do wymyslenia jak ogarnac Command wzorca
        //TODO pierwsze klawiszy jak cos to 1..5 dla trybow myszki, esc dla menu
        return false;
    }

    public boolean keyUp(final int keycode) {
        return false;
    }

    public boolean keyTyped(final char character) {
        return false;
    }
}
