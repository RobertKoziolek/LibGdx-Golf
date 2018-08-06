package com.robcio.golf.listener.box2d;

import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.box2d.holder.ContactInfoHolder;

public interface BodyListener {
    void beginContact(final ContactInfoHolder contactInfoHolder);
    void endContact(final ContactInfoHolder contactInfoHolder);
    EntityFlags[] getEntityFlagsA();
    EntityFlags[] getEntityFlagsB();
}
