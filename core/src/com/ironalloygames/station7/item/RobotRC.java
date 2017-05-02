package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;
import com.ironalloygames.station7.actor.Actor;

public class RobotRC extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2834915695873105729L;

	@Override
	protected Sprite getSprite() {
		return Sprites.robotRC[0];
	}

	@Override
	public String getDescription() {
		return "RC Robot<br/><br/>This robot has been improved with a radio, allowing you to control it remotely if you have a remote control. Click it again to launch.";
	}
	
	@Override
	public void use() {
		//System.out.println("ROBOT LAUNCH " + Game.s.state.player.getVisualAngle());
		
		for(Actor a : Game.s.state.actors)
		{
			if(a instanceof com.ironalloygames.station7.actor.Robot) ((com.ironalloygames.station7.actor.Robot)a).destroy();
			if(a instanceof com.ironalloygames.station7.actor.RobotRC) ((com.ironalloygames.station7.actor.RobotRC)a).destroy();
		}
		
		com.ironalloygames.station7.actor.RobotRC bot = new com.ironalloygames.station7.actor.RobotRC();
		bot.gameStart();
		bot.setPosition(Game.s.state.player.getPosition(), Game.s.state.player.getVisualAngle());
		
		Game.s.state.addActor(bot);
		
		super.use();
	}
	
	@Override
	public String getTitle() {
		return "RC Robot";
	}
}
