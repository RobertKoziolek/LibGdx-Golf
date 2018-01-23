package com.robcio.golf.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.robcio.golf.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= MainClass.WIDTH;
		config.height= MainClass.HEIGHT;
		config.title=MainClass.TITLE;
		final boolean debug;
		if (arg.length<1){
			debug = false;
		} else{
			debug = Boolean.parseBoolean(arg[0]);
		}
		new LwjglApplication(new MainClass(debug), config);
	}
}
