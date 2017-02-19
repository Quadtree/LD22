package com.ironalloygames.station7.actor;

import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.item.Item;
import com.ironalloygames.station7.item.Manual;

public class DiagnosticConsole extends Console {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8924393917203787623L;
	
	transient boolean unlocked = false;

	@Override
	protected void interactedWith() {
		
		Item itm = null;
		
		for(Item i : Game.s.state.player.getInventory())
		{
			if(i instanceof Manual) itm = i;
		}
		
		if(itm != null)
		{
			Game.s.state.player.getInventory().remove(itm);
			Game.s.playerGUI.update(Game.s.state.player);
			unlocked = true;
		}
		
		if(unlocked)
		{
			Game.s.playerGUI.setVisible(false);
			Game.s.diagnosticConsoleGUI.setVisible(true);
			Sounds.screen.play();
		} else {
			Game.s.playerGUI.addMessage("You can't understand this console. If only you had a manual!");
		}
	}

}
