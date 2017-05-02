package com.ironalloygames.station7.gui;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import java.util.List;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.actor.Actor;
import com.ironalloygames.station7.actor.DiagnosticLockedDoor;
import com.ironalloygames.station7.actor.Player;
import com.ironalloygames.station7.item.Item;

import de.matthiasmann.twl.Alignment;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.TextArea;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.textarea.HTMLTextAreaModel;
import de.matthiasmann.twl.textarea.TextAreaModel;

public class RobotGUI extends Widget {
	final Button exitControlButton = new Button();
	
	public RobotGUI()
	{
		exitControlButton.setText("Exit Robot Control");
		
		exitControlButton.addCallback(new Runnable(){

			@Override
			public void run() {
				Game.s.state.conObj = Game.s.state.player;
				Game.s.playerGUI.setVisible(true);
				Game.s.robotGUI.setVisible(false);
				Sounds.click.play();
			}
		});
		
		add(exitControlButton);
		
		this.setVisible(false);
	}

	@Override
	protected void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		exitControlButton.setSize(300, 90);
		exitControlButton.setAlignment(Alignment.CENTER);
		exitControlButton.setPosition(this.getInnerWidth() / 2 - exitControlButton.getWidth() / 2, 50);
		
		super.layout();
	}
}
