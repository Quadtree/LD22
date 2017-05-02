package com.ironalloygames.station7.actor;

import java.io.Serializable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ironalloygames.station7.GameState;

public abstract class Actor implements Serializable, Comparable<Actor>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6304109988246666431L;
	
	public void gameStart(){}
	public void render(SpriteBatch batch){}
	public void update(){}
	public boolean keep(){ return true; }
	public void destroyed(){}
	public int getRenderPriority(){ return 0; }
	
	@Override
	public int compareTo(Actor arg0) {
		return arg0.getRenderPriority() - this.getRenderPriority();
	}
}
