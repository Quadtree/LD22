package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class ManualRightHalf extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2502862888513186702L;

	@Override
	protected Sprite getSprite() {
		return Sprites.manualRightHalf;
	}

	@Override
	public String getDescription() {
		return "Manual Right Half<br/><br/>The right part of a manual that someone seems to have torn in half.";
	}
	
	@Override
	public String getTitle() {
		return "Right Half";
	}
}
