package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.robcio.golf.component.graphics.LightningSingleLine;
import com.robcio.golf.component.graphics.Line;
import com.robcio.golf.enumeration.TextureId;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;
import com.robcio.golf.utils.Maths;

public class LightningRenderSystem extends BatchIteratingSystem {

    final private TextureAtlas.AtlasRegion lightningEnd, lightning;

    public LightningRenderSystem(final int priority, final SpriteBatch batch) {
        super(Family.all(LightningSingleLine.class)
                    .get(), priority, batch);
        this.lightning = Assets.getRegion(TextureId.LIGHTNING);
        this.lightningEnd = Assets.getRegion(TextureId.LIGHTNING_END);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final Line line = Mapper.lightningSingleLine.get(entity);
        final Vector2 A = line.start.getVector2();
        final Vector2 B = line.end.getVector2();

        final Vector2 tangent = B.sub(A);
        float rotation = (float) Math.atan2(tangent.y, tangent.x);
        float angle = Maths.radiansToDegrees(rotation);

        float ImageThickness = 4;
        float thicknessScale = 1 / ImageThickness;

        float lightningHalfHeight = lightning.getRegionHeight() / 2f;

        setAlpha(0.6f);
        getBatch().draw(lightning, A.x, A.y - lightningHalfHeight,
                        0, lightningHalfHeight,
                        lightning.getRegionWidth(), lightning.getRegionHeight(),
                        tangent.len(), thicknessScale,
                        angle);
        getBatch().draw(lightningEnd, A.x - lightningEnd.getRegionWidth(), A.y - lightningHalfHeight,
                        lightningEnd.getRegionWidth(), lightningEnd.getRegionHeight() / 2f,
                        lightningEnd.getRegionWidth(), lightningEnd.getRegionHeight(),
                        thicknessScale, thicknessScale, angle);
        final Vector2 endPositionn = B.add(A);
        getBatch().draw(lightningEnd, endPositionn.x - lightningEnd.getRegionWidth(),
                        endPositionn.y - lightningHalfHeight,
                        lightningEnd.getRegionWidth(), lightningEnd.getRegionHeight() / 2f,
                        lightningEnd.getRegionWidth(), lightningEnd.getRegionHeight(),
                        thicknessScale, thicknessScale, angle + 180);
        resetAlpha();
    }
}
