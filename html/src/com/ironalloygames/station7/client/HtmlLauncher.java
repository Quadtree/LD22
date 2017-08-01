package com.ironalloygames.station7.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.ironalloygames.station7.Game;

public class HtmlLauncher extends GwtApplication {

	@Override
	public ApplicationListener createApplicationListener() {
		return new Game();
	}

	@Override
	public GwtApplicationConfiguration getConfig() {
		return new GwtApplicationConfiguration(1024, 768);
	}
}