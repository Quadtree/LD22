package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class TankBroken extends Tank {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8314323452255348194L;

	@Override
	protected Sprite getSprite() {
		return Sprites.tankBroken;
	}

	@Override
	protected String getMessage() {
		return "Looks like someone didn't like this one.";
	}

}
