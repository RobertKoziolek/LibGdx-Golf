package com.robcio.golf.listener;

import com.robcio.golf.enumeration.EntityFlags;

public interface BodyListener {
    void beginContact(final EntityHolder entityHolder);
    void endContact(final EntityHolder entityHolder);
    EntityFlags[] getEntityFlagsA();
    EntityFlags[] getEntityFlagsB();
}
