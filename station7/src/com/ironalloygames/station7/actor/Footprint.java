package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class Footprint extends Scenery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6429456769283956073L;

	@Override
	protected Sprite getSprite() {
		return Sprites.footprint;
	}

	@Override
	protected float getSize() {
		return 0.2f;
	}

	@Override
	protected boolean isSensor() {
		return true;
	}
}
