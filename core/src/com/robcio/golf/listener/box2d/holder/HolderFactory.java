package com.robcio.golf.listener.box2d.holder;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HolderFactory {
    private static ContactInfoHolder EMPTY = new EmptyEntityHolder();

    public static ContactInfoHolder create(final Contact contact) {
        final Body bodyA = contact.getFixtureA()
                                  .getBody();
        final Body bodyB = contact.getFixtureB()
                                  .getBody();
        try {
            return new EntityHolder(bodyA, bodyB);
        } catch (final ClassCastException | IllegalArgumentException e) {
            return EMPTY;
        }
    }
}
