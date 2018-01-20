package com.robcio.golf;

import com.badlogic.gdx.Gdx;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Log {
    private static String DEFAULT_PREFIX = "Log";

    public static void e(String msg) {
        e(DEFAULT_PREFIX, msg);
    }

    public static void d(String msg) {
        d(DEFAULT_PREFIX, msg);
    }

    public static void i(String msg) {
        i(DEFAULT_PREFIX, msg);
    }

    public static void e(String tag, String msg) {
        Gdx.app.error(tag, msg);
    }

    public static void d(String tag, String msg) {
        Gdx.app.debug(tag, msg);
    }

    public static void i(String tag, String msg) {
        Gdx.app.log(tag, msg);
    }
}
