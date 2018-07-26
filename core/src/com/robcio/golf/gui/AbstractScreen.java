package com.robcio.golf.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.robcio.golf.MainClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public abstract class AbstractScreen implements Screen {
    private Stage stage;

    public AbstractScreen(@NonNull final Camera camera) {
        this.stage = new Stage(new FitViewport(MainClass.WIDTH, MainClass.HEIGHT, camera));
    }

    @Override
    abstract public void show();

    @Override
    public void render(final float delta) {
        update(delta);
        stage.draw();
    }

    protected void update(final float deltaTime) {
        stage.act(deltaTime);
    }

    @Override
    public void resize(final int width, final int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    protected final void setStage(@NonNull final Stage stage) {
        if (this.stage != null) throw new IllegalStateException("Cannot set another stage to a screen");
        this.stage = stage;
    }

    public InputProcessor[] getInputs() {
        return new Stage[]{stage};
    }
}
