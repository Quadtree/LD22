package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class RobotMotor extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2834915695873105729L;

	@Override
	protected Sprite getSprite() {
		return Sprites.motor;
	}

	@Override
	public String getDescription() {
		return "Motor<br/><br/>This powerful motor looks great for making a robot. If only you had some tracks!";
	}
	
	@Override
	public Item tryCombineWith(Item i) {
		if(i instanceof RobotTracks)
			return new Robot();
		else
			return super.tryCombineWith(i);
	}
	
	@Override
	public String getTitle() {
		return "Motor";
	}
}
