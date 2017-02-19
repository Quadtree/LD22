package com.ironalloygames.station7.actor;

import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;

public class MasterConsole extends Console {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7009714406733919439L;

	@Override
	protected void interactedWith() {
		Game.s.playerGUI.setVisible(false);
		Game.s.masterConsoleGUI.setVisible(true);
		Sounds.screen.play();
	}

}
