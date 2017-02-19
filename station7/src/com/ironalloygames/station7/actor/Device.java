package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class Device extends Scenery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5438800541992042444L;

	@Override
	protected Sprite getSprite() {
		return Sprites.device;
	}

	@Override
	protected float getSize() {
		return 1.7f;
	}

}
