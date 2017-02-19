package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;

public class Core extends Scenery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3956723096792834650L;

	@Override
	protected Sprite getSprite() {
		return Sprites.core;
	}

	@Override
	protected float getSize() {
		return 3;
	}

	@Override
	public void update() {
		if(!Game.s.state.tramOn) Game.s.state.addActor((new Fire(pos, (float)(Math.random() * Math.PI * 2))));
		super.update();
	}

	@Override
	protected short getCollisionGroupIndex() {
		return -150;
	}
}
