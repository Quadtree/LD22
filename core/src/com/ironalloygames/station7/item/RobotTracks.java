package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class RobotTracks extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2834915695873105729L;

	@Override
	protected Sprite getSprite() {
		return Sprites.tracks;
	}

	@Override
	public String getDescription() {
		return "Tracks<br/><br/>These grippy tracks look great for making a robot. If only you had a motor!";
	}
	
	@Override
	public String getTitle() {
		return "Tracks";
	}
}
