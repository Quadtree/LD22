package com.ironalloygames.station7.editor;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class EditorMain {
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration conf = new LwjglApplicationConfiguration();
		
		conf.width = 1920;
		conf.height = 1080;
		conf.depth = 32;
		conf.fullscreen = false;
		conf.title = "Station 7 Editor";
		conf.useGL20 = true;
		
		new LwjglApplication(new EditorGame(), conf);
	}
}
