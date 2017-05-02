package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;
import com.ironalloygames.station7.actor.Actor;
import com.ironalloygames.station7.actor.RobotRC;

public class RemoteControl extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2834915695873105729L;
	
	@Override
	protected Sprite getSprite() {
		return Sprites.remote;
	}

	@Override
	public String getDescription() {
		return "Remote Control<br/><br/>This jury-rigged remote control allows you to control the remote controlled robot after you deploy it. Click it again to activate.";
	}

	@Override
	public void use() {
		com.ironalloygames.station7.actor.RobotRC bot = null;
		
		for(Actor a : Game.s.state.actors)
		{
			if(a instanceof com.ironalloygames.station7.actor.RobotRC) bot = (com.ironalloygames.station7.actor.RobotRC) a;
		}
		
		if(bot != null)
		{
			Game.s.state.conObj = bot;
			Game.s.playerGUI.setVisible(false);
			Game.s.robotGUI.setVisible(true);
		}
		
		super.use();
	}
	
	@Override
	public String getTitle() {
		return "Remote Control";
	}
}
