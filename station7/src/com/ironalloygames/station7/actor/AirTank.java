package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class AirTank extends Scenery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5896250229621444357L;

	@Override
	protected Sprite getSprite() {
		return Sprites.airTank;
	}

	@Override
	protected float getSize() {
		return 0.5f;
	}

}
