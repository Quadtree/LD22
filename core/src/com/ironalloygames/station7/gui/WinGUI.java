package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class WinGUI extends WidgetAdapter {

	public WinGUI() {
		this.setBackgroundImage(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/victory.png")))));
		this.setVisible(false);
	}

	@Override
	public void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		super.layout();
	}
}
