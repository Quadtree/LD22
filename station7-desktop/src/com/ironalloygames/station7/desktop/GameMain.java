package com.ironalloygames.station7.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ironalloygames.station7.Game;

public class GameMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration conf = new LwjglApplicationConfiguration();
		
		
		if(args.length == 0 || !args[0].equals("-fs"))
		{
			conf.width = 1024;
			conf.height = 768;
			conf.depth = 32;
			conf.fullscreen = false;
		} else {
			long bestPx = 0;
			DisplayMode bestDM = null;
			
			for(DisplayMode dm : LwjglApplicationConfiguration.getDisplayModes())
			{
				long px = dm.width*dm.height*dm.bitsPerPixel;
				if(px > bestPx)
				{
					bestPx = px;
					bestDM = dm;
				}
			}
			
			conf.setFromDisplayMode(bestDM);
			conf.fullscreen = true;
		}
		conf.title = "Station 7";
		conf.useGL20 = true;
		conf.vSyncEnabled = true;
		
		new LwjglApplication(new Game(), conf);
	}

}
