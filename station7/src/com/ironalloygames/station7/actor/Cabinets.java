package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class Cabinets extends Scenery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8831116137419052651L;

	@Override
	protected Sprite getSprite() {
		return Sprites.cabinets;
	}

	@Override
	protected float getSize() {
		return 0.8f;
	}

	@Override
	protected boolean onSquare() {
		return true;
	}
}
