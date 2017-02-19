package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.actor.Actor;
import com.ironalloygames.station7.actor.FlameNozzle;

import de.matthiasmann.twl.*;

public class SecurityConsoleGUI extends Widget {
	final Label warningLabel = new Label();
	final Label pyroLabel = new Label();
	final Button pyroButton = new Button();
	final Label bridgesLabel = new Label();
	final Button bridgesButton = new Button();
	final Button closeButton = new Button();
	
	public SecurityConsoleGUI()
	{
		warningLabel.setText("!!! CODE RED !!!");
		add(warningLabel);
		
		pyroLabel.setText("Pyrodefense ONLINE");
		add(pyroLabel);
		pyroButton.setText("Disable");
		add(pyroButton);
		
		bridgesLabel.setText("Rift Bridges RETRACTED");
		add(bridgesLabel);
		bridgesButton.setText("Extend");
		add(bridgesButton);
		
		pyroButton.addCallback(new Runnable(){

			@Override
			public void run() {
				for(Actor a : Game.s.state.actors)
				{
					if(a instanceof FlameNozzle)
					{
						((FlameNozzle)a).disabled = true;
						pyroLabel.setText("Pyrodefense OFFLINE");
					}
				}
				Sounds.click.play();
			}
		});
		
		closeButton.addCallback(new Runnable(){

			@Override
			public void run() {
				SecurityConsoleGUI.this.setVisible(false);
				Game.s.playerGUI.setVisible(true);
				Sounds.click.play();
			}
		});
		
		bridgesButton.addCallback(new Runnable(){

			@Override
			public void run() {
				Game.s.state.raiseBridges();
				bridgesLabel.setText("Rift Bridges EXTENDED");
				Sounds.click.play();
			}
		});
		
		closeButton.setText("Close Console");
		add(closeButton);
		
		this.setVisible(false);
	}

	@Override
	protected void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		warningLabel.setPosition(100, 100);
		
		pyroLabel.setPosition(100, 300);
		
		pyroButton.setPosition(450, 250);
		pyroButton.setAlignment(Alignment.CENTER);
		pyroButton.setSize(250, 90);
		
		bridgesLabel.setPosition(100, 400);
		
		bridgesButton.setPosition(450, 350);
		bridgesButton.setAlignment(Alignment.CENTER);
		bridgesButton.setSize(250, 90);
		
		closeButton.setPosition(this.getInnerRight() - 300, this.getInnerBottom() - 150);
		closeButton.setAlignment(Alignment.CENTER);
		closeButton.setSize(250, 90);
		
		super.layout();
	}
	
	public void update()
	{
		warningLabel.setVisible(System.currentTimeMillis() / 400 % 2 == 0);
	}
}
