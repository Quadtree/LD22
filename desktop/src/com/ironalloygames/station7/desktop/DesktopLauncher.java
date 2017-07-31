package com.ironalloygames.station7.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.ironalloygames.station7.Game;

public class DesktopLauncher {
	public static void main(String[] arg) {

		TexturePacker.processIfModified("../../unpacked_assets", ".", "./main");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 768;
		new LwjglApplication(new Game(), config);
	}
}
