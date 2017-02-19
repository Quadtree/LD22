package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class Bed extends Scenery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7046266733031566413L;

	@Override
	protected Sprite getSprite() {
		return Sprites.bed;
	}

	@Override
	protected float getSize() {
		return 0.6f;
	}

	@Override
	protected boolean onSquare() {
		return true;
	}
}
