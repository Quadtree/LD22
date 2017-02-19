package com.ironalloygames.station7.actor;

import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;

public class SecurityConsole extends Console {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2182804129775435165L;

	@Override
	protected void interactedWith() {
		Game.s.playerGUI.setVisible(false);
		Game.s.securityConsoleGUI.setVisible(true);
		Sounds.screen.play();
	}

}
