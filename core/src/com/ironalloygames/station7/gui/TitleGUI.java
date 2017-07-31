package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;

public class TitleGUI extends com.badlogic.gdx.scenes.scene2d.ui.Widget {

	public TitleGUI() {
		this.setVisible(true);
	}

	@Override
	protected void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		super.layout();
	}
}
