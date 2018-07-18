package com.robcio.golf.component.graphics;

import com.badlogic.ashley.core.Component;
import lombok.NonNull;

public class FadeOut implements Component {
    public float time;
    public float alpha;
    final public float rate;

    private FadeOut(@NonNull final float time) {
        this.time = time;
        this.alpha = 1f;
        this.rate = alpha / time;
    }

    public static FadeOut of(@NonNull final float time) {
        return new FadeOut(time);
    }
}
