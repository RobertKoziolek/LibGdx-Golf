package com.robcio.golf.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.robcio.golf.enumeration.TextureId;
import lombok.Getter;

public class Assets {

    private static final AssetManager assetManager = new AssetManager();

    private static final String UISKIN_ATLAS = "ui/uiskin.atlas";
    private static final String UISKIN_JSON = "ui/uiskin.json";
    private static final String FONT = "default-font";
    private static final String FONT_FNT = "font/modak32.fnt";
    private static final String FONT_PNG = "font/modak32.png";

    @Getter
    static private Skin skin;
    @Getter
    static private BitmapFont font;

    public static void initialize() {
        loadFont();
        loadSkin();
        Textures.load();
        //TODO do wyciagniecia pod ekran ladowania czy cos
        assetManager.finishLoading();
    }

    private static void loadFont() {
        font = new BitmapFont(Gdx.files.internal(FONT_FNT), Gdx.files.internal(FONT_PNG),
                              false);
    }

    private static void loadSkin() {
        assetManager.load(UISKIN_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        skin = new Skin(assetManager.get(UISKIN_ATLAS, TextureAtlas.class));
        skin.add(FONT, font);
        skin.load(Gdx.files.internal(UISKIN_JSON));
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
