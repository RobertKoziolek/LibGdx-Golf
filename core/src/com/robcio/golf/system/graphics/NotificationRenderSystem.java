package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.component.util.NotificationData;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;

//TODO NOTIF poolowanie notyfikacji, rysuje kazdo jak leco
//TODO NOTIF dodac jakis czarny polprzezroczysty blok czy cos poza
//TODO NOTIF animacja wjezdzajacego tekstu jak w hotsie czy cos
public class NotificationRenderSystem extends BatchIteratingSystem {

    public NotificationRenderSystem(final int priority, final SpriteBatch batch) {
        super(Family.all(NotificationData.class)
                    .get(), priority, batch);
    }

    //TODO notyfikacja jako komponent moze wyskakiwac z entity, jak dymki obrazen itd
    //TODO NOTIF ogarnac jak zrobic to na srodek
    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final NotificationData data = Mapper.notificationData.get(entity);

        Assets.getFont()
              .draw(getBatch(), data.text, data.x, data.y);
    }
}
