package com.robcio.golf.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.golf.utils.Assets;
import com.robcio.golf.utils.ButtonListener;
import com.robcio.golf.utils.Command;

import java.util.Observable;

public class GuiAssembler {

    public static Table tableOf(final Button... buttons) {
        final Table table = new Table(Assets.getSkin());
        for (final Button button: buttons) {
            table.add(button);
        }
        return table;
    }

    public static PaneAssembler paneOf(final Actor widget) {
        return new PaneAssembler(widget);
    }

    public static TextButtonAssembler textButtonOf(final String text) {
        return new TextButtonAssembler(text);
    }

    public static class TextButtonAssembler {
        private final TextButton button;
        private float width, height;
        private float x, y;
        private Observable observable;
        private Command command;

        private TextButtonAssembler(final String text) {
            this.button = new TextButton(text, Assets.getSkin());
        }

        public TextButton assemble() {
            button.setSize(width, height);
            button.setPosition(x, y);
            if (command == null) throw new IllegalArgumentException("Cannot assemble a button without any action");
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    command.execute();
                }
            });
            if (observable != null) {
                observable.addObserver(new ButtonListener(button));
            }
            return button;
        }

        public TextButtonAssembler withSize(final float width, final float height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public TextButtonAssembler withPosition(final float x, final float y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public TextButtonAssembler withCommand(final Command command) {
            this.command = command;
            return this;
        }

        public TextButtonAssembler observing(final Observable observable) {
            this.observable = observable;
            return this;
        }
    }

    public static class PaneAssembler {
        private final ScrollPane pane;
        private float width, height;
        private boolean scrollingDisabledX, scrollingDisabledY;
        private float fadeAlphaSeconds = 1f, fadeDelaySeconds = 1f;
        private float overscrollDistance = 50, overscrollSpeedMin = 30, overscrollSpeedMax = 200;

        private PaneAssembler(final Actor widget) {
            this.pane = new ScrollPane(widget, Assets.getSkin());
        }

        public ScrollPane assemble() {
            pane.setSize(width, height);
            pane.setScrollingDisabled(scrollingDisabledX, scrollingDisabledY);
            pane.setupFadeScrollBars(fadeAlphaSeconds, fadeDelaySeconds);
            pane.setupOverscroll(overscrollDistance, overscrollSpeedMin, overscrollSpeedMax);
            return pane;
        }

        public PaneAssembler withSize(final float width, final float height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public PaneAssembler withScrollingDisabled(final boolean scrollingDisabledX, final boolean scrollingDisabledY) {
            this.scrollingDisabledX = scrollingDisabledX;
            this.scrollingDisabledY = scrollingDisabledY;
            return this;
        }

        public PaneAssembler withFadeScrollBars(final float fadeAlphaSeconds, final float fadeDelaySeconds) {
            this.fadeAlphaSeconds = fadeAlphaSeconds;
            this.fadeDelaySeconds = fadeDelaySeconds;
            return this;
        }

        public PaneAssembler withOverscroll(final float distance, float speedMin, float speedMax) {
            this.overscrollDistance = distance;
            this.overscrollSpeedMin = speedMin;
            this.overscrollSpeedMax = speedMax;
            return this;
        }
    }
}
