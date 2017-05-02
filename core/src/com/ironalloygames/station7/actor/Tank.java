package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class Tank extends Scenery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3828020734271816754L;

	@Override
	protected Sprite getSprite() {
		return Sprites.tank;
	}

	@Override
	protected float getSize() {
		return 0.8f;
	}

	@Override
	protected boolean onSquare() {
		return true;
	}

	@Override
	protected String getMessage() {
		return "It almost looks like there's someone in there...";
	}
}
