package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Sprites;

public class Radio extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2834915695873105729L;
	
	@Override
	protected Sprite getSprite() {
		return Sprites.radio;
	}

	@Override
	public String getDescription() {
		return "Radio<br/><br/>This radio looks good for making something remote controlled...";
	}
	
	@Override
	public Item tryCombineWith(Item i) {
		if(i instanceof Joystick)
			return new RemoteControl();
		else if(i instanceof Robot)
			return new RobotRC();
		else
			return super.tryCombineWith(i);
	}
	
	@Override
	public String getTitle() {
		return "Radio";
	}
}
