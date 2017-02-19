package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class ManualLeftHalf extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7163330868844869060L;

	@Override
	protected Sprite getSprite() {
		return Sprites.manualLeftHalf;
	}

	@Override
	public String getDescription() {
		return "Manual Left Half<br/><br/>The left part of a manual that someone seems to have torn in half.";
	}

	@Override
	public Item tryCombineWith(Item i) {
		if(i instanceof ManualRightHalf)
			return new Manual();
		else
			return super.tryCombineWith(i);
	}
	
	@Override
	public String getTitle() {
		return "Left Half";
	}
}
