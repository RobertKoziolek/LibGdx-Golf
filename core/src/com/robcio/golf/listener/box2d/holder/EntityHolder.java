package com.robcio.golf.listener.box2d.holder;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Bits;
import com.robcio.golf.enumeration.EntityFlags;
import lombok.Getter;
import lombok.NonNull;

public class EntityHolder implements ContactInfoHolder{
    private final static int FLAG_NONE = EntityFlags.NONE.getId();

    @Getter
    private Entity A, B;
    @Getter
    private Body bodyA, bodyB;

    public EntityHolder(@NonNull final Body bodyA, @NonNull final Body bodyB) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        try {
            this.A = (Entity) bodyA.getUserData();
            this.B = (Entity) bodyB.getUserData();
        } catch (final ClassCastException e) {
            throw e;
        }
        if (this.A == null || this.B == null || this.A.flags == FLAG_NONE || this.B.flags == FLAG_NONE) {
            throw new IllegalArgumentException("Holder needs to hold valid entities");
        }
    }

    public boolean containsFlags(final EntityFlags[] flagsA, final EntityFlags[] flagsB) {
        final Bits bitsA = new Bits();
        final Bits bitsB = new Bits();
        bitsA.set(A.flags);
        bitsB.set(B.flags);

        if (bitsA.intersects(getBits(flagsA)) && bitsB.intersects(getBits(flagsB))) {
            return true;
        }
        if (bitsA.intersects(getBits(flagsB)) && bitsB.intersects(getBits(flagsA))) {
            correct();
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private Bits getBits(final EntityFlags[] flags) {
        final Bits bits = new Bits();
        for (EntityFlags flag: flags) {
            bits.set(flag.getId());
        }
        return bits;
    }

    private void correct() {
        final Entity temp = A;
        A = B;
        B = temp;
        final Body tempBody = this.bodyA;
        bodyA = bodyB;
        bodyB = tempBody;
    }
}
