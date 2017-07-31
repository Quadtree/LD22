package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class Joystick extends Item {

	/**
	 *
	 */
	private static final long serialVersionUID = 2834915695873105729L;

	@Override
	public String getDescription() {
		return "Joystick<br/><br/>This is the joystick you grabbed off the control panel. It seems like it might come in handy.";
	}

	@Override
	protected Sprite getSprite() {
		return Sprites.joystick;
	}

	@Override
	public String getTitle() {
		return "Joystick";
	}
}
