package com.ironalloygames.station7.gui;

import javax.swing.GroupLayout.Alignment;

import com.badlogic.gdx.Gdx;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;

public class RobotGUI extends com.badlogic.gdx.scenes.scene2d.ui.Widget {
	final Button exitControlButton = new Button();

	public RobotGUI() {
		exitControlButton.setText("Exit Robot Control");

		exitControlButton.addCallback(new Runnable() {

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
