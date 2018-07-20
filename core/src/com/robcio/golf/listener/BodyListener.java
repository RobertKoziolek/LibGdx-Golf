package com.robcio.golf.listener;

import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.enumeration.EntityFlags;

import java.util.Map;

public interface BodyListener {
    void beginContact(final Map<Integer, Body> map);
    void endContact(final Map<Integer, Body> map);
    EntityFlags[] getEntityFlagsA();
    EntityFlags[] getEntityFlagsB();
}
