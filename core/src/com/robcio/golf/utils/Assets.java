package com.robcio.golf.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.robcio.golf.enumeration.TextureId;
import lombok.Getter;

public class Assets {

    final static private AssetManager assetManager = new AssetManager();
    @Getter
    static private Skin skin;
    static private BitmapFont font;

    public static void initialize() {
        loadFont();
        loadSkin();
        Textures.load();
        //TODO do wyciagniecia pod ekran ladowania czy cos
        assetManager.finishLoading();
    }

    private static void loadFont() {
        font = new BitmapFont(Gdx.files.internal("font/modak32.fnt"), Gdx.files.internal("font/modak32.png"),
                              false);
    }

    private static void loadSkin() {
        assetManager.load("ui/uiskin.atlas", TextureAtlas.class);
        assetManager.finishLoading();
        skin = new Skin(assetManager.get("ui/uiskin.atlas", TextureAtlas.class));
        skin.add("default-font", font);
        skin.load(Gdx.files.internal("ui/uiskin.json"));
    }

    public static void dispose() {
        assetManager.dispose();
        font.dispose();
        skin.dispose();
    }

    public static class Textures {
        final private static String directory = "texture/";

        public static Texture get(final TextureId textureId) {
            return assetManager.get(getPath(textureId), Texture.class);
        }

        static void load() {
            for (TextureId textureId : TextureId.values()) {
                assetManager.load(getPath(textureId), Texture.class);
            }
        }

        private static String getPath(TextureId textureId) {
            return directory + textureId.getFilename();
        }
    }
}
