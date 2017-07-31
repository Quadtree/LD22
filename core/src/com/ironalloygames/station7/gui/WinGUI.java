package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;

public class WinGUI extends com.badlogic.gdx.scenes.scene2d.ui.Widget {

	public WinGUI() {
		this.setVisible(false);
	}

	@Override
	protected void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		super.layout();
	}
}
