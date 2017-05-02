package com.ironalloygames.station7.actor;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Shape;

public class Interactable extends Entity {

	@Override
	protected Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected float getDensity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean isSensor() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BodyType getBodyType() {
		// TODO Auto-generated method stub
		return null;
	}

}
