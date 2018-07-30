package com.robcio.golf.listener.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.robcio.golf.component.graphics.FadeOut;
import com.robcio.golf.component.structure.Force;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.entity.light.LightEntity;
import com.robcio.golf.entity.util.Notification;
import com.robcio.golf.enumeration.EntityFlags;
import com.robcio.golf.listener.EntityHolder;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;
import lombok.Getter;

@Getter
public class BumperListener extends EngineBodyListener {

    private final EntityFlags[] entityFlagsA = {EntityFlags.BUMPER};
    private final EntityFlags[] entityFlagsB = {EntityFlags.BALL};

    public BumperListener(final Engine engine) {
        super(engine);
    }

    public void beginContact(final EntityHolder entityHolder) {
        //TODO rozwiazuje problem lepienia sie do tego, ale moze odbija 2x
        endContact(entityHolder);
    }

    @Override
    public void endContact(final EntityHolder entityHolder) {
        final Body bumper = entityHolder.getBodyA();
        final Body ball = entityHolder.getBodyB();
        final Force force = Mapper.force.get(entityHolder.getA());

        final Vector2 distance = Maths.getDistance(ball.getPosition(), bumper.getPosition());
        final float finalDistance = distance.len();

        ball.applyForceToCenter(distance.scl((1 / Maths.getVectorSum(distance)) * force.value / finalDistance), true);
        engine.addEntity(new Notification("A ball hit the bumper here"));

        final Position position = Mapper.position.get(entityHolder.getA());
        final LightEntity lightEntity = new LightEntity(position, Force.of(1f));
        lightEntity.add(FadeOut.of(0.4f));
        engine.addEntity(lightEntity);
    }
}
