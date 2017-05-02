package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;
import com.ironalloygames.station7.actor.Actor;

public class Robot extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2834915695873105729L;

	@Override
	protected Sprite getSprite() {
		return Sprites.robot[0];
	}

	@Override
	public String getDescription() {
		return "Robot<br/><br/>This powerful robot can block or smash through things. Click it again to activate, or to recall and re-launch.";
	}

	@Override
	public void use() {
		//System.out.println("ROBOT LAUNCH " + Game.s.state.player.getVisualAngle());
		
		for(Actor a : Game.s.state.actors)
		{
			if(a instanceof com.ironalloygames.station7.actor.Robot) ((com.ironalloygames.station7.actor.Robot)a).destroy();
		}
		
		com.ironalloygames.station7.actor.Robot bot = new com.ironalloygames.station7.actor.Robot();
		bot.gameStart();
		bot.setPosition(Game.s.state.player.getPosition(), Game.s.state.player.getVisualAngle());
		
		Game.s.state.addActor(bot);
		
		super.use();
	}
	
	@Override
	public String getTitle() {
		return "Robot";
	}
}
