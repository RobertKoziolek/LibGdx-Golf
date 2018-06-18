package com.robcio.golf.control;

import com.badlogic.ashley.core.EntitySystem;

public interface MouseMode {

    void before();

    void after();

    boolean touchDown();

    boolean touchUp();

    boolean touchDragged();

    void changeSystemProcessing(final boolean processing);

    Class<? extends EntitySystem> getSystemClass();

    String getTooltip();
}
