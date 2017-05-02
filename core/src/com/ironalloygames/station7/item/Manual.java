package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class Manual extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7165475664256985747L;

	@Override
	protected Sprite getSprite() {
		return Sprites.manual;
	}

	@Override
	public String getDescription() {
		return "Manual<br/><br/>This manual appears to describe how to use the Diagnostic Console in Station 7.";
	}

	@Override
	public String getTitle() {
		return "Manual";
	}
}
