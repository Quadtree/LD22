package com.ironalloygames.station7.actor;

import com.badlogic.gdx.math.Vector2;

public interface ControlledObject {
	public Vector2 getPosition();
	public void setDestination(Vector2 dest);
}
