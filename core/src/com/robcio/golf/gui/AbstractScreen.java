package com.robcio.golf.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.robcio.golf.MainClass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class AbstractScreen implements Screen {
    private Stage stage;
//    protected Color backgroundColor = Color.GREEN;

    public AbstractScreen(final Camera camera) {
        if (camera == null) throw new IllegalStateException("ScreenId cannot be initialized with camera as null");
        this.stage = new Stage(new FitViewport(MainClass.WIDTH, MainClass.HEIGHT, camera));
    }

    @Override
    abstract public void show();

    @Override
    public void render(final float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
//        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    //TODO na protected
    public void update(final float deltaTime) {
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

    protected final void setStage(final Stage stage) {
        if (this.stage != null) throw new IllegalStateException("Cannot set another stage to a screen");
        if (stage == null) throw new IllegalArgumentException("Cannot set stage to null");
        this.stage = stage;
    }

    public InputProcessor[] getInputs() {
        return new Stage[]{stage};
    }
}
