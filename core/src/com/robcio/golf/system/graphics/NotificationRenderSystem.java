package com.robcio.golf.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.golf.MainClass;
import com.robcio.golf.component.util.NotificationData;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.Mapper;

//TODO NOTIF poolowanie notyfikacji, rysuje kazdo jak leco
//TODO NOTIF dodac jakis czarny polprzezroczysty blok czy cos poza
//TODO NOTIF animacja wjezdzajacego tekstu jak w hotsie czy cos
public class NotificationRenderSystem extends IteratingSystem {

    final private SpriteBatch batch;

    public NotificationRenderSystem(final int priority, final SpriteBatch batch) {
        super(Family.all(NotificationData.class)
                    .get(), priority);
        this.batch = batch;
    }

    //TODO NOTIF musi batchowac po mapie, zeby mapa nie zaslaniala
    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    //TODO NOTIF ogarnac jak zrobic to na srodek
    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final String text = Mapper.notificationData.get(entity).text;

        Assets.getFont().draw(batch, text, MainClass.WIDTH/4, MainClass.HEIGHT-80);
    }
}
