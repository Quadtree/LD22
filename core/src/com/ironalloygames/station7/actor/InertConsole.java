package com.ironalloygames.station7.actor;

import com.ironalloygames.station7.Game;

public class InertConsole extends Console {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1174222004772059822L;

	@Override
	protected void interactedWith() {
		Game.s.playerGUI.addMessage("The text on this console is uninteresting.");
	}

}
