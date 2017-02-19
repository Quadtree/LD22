package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class HardwareShelf extends Scenery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3965505552632069563L;

	@Override
	protected Sprite getSprite() {
		return Sprites.hardwareShelf;
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
