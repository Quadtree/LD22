package com.ironalloygames.station7.gui;

import javax.swing.GroupLayout.Alignment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.actor.Actor;
import com.ironalloygames.station7.actor.DiagnosticLockedDoor;

public class DiagnosticConsoleGUI extends Widget {
	final Button closeButton = new Button();
	final Button disengageButton = new Button();
	final Label lockdownLabel = new Label("", null);
	final Label warningLabel = new Label("", null);

	public DiagnosticConsoleGUI() {
		warningLabel.setText("!!! CODE RED !!!");
		add(warningLabel);
		lockdownLabel.setText("Sector lockdown in effect.");
		add(lockdownLabel);

		disengageButton.setText("Disengage");
		add(disengageButton);

		disengageButton.addCallback(new Runnable() {

			@Override
			public void run() {
				for (Actor a : Game.s.state.actors) {
					if (a instanceof DiagnosticLockedDoor) {
						((DiagnosticLockedDoor) a).open();
						lockdownLabel.setText("Sector lockdown disengaged.");
					}
				}
				Sounds.click.play();
				Sounds.door.play();
			}
		});

		closeButton.addCallback(new Runnable() {

			@Override
			public void run() {
				DiagnosticConsoleGUI.this.setVisible(false);
				Game.s.playerGUI.setVisible(true);
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

		lockdownLabel.setPosition(100, this.getInnerBottom() - 200);

		disengageButton.setPosition(450, this.getInnerBottom() - 250);
		disengageButton.setAlignment(Alignment.CENTER);
		disengageButton.setSize(250, 90);

		closeButton.setPosition(this.getInnerRight() - 300, this.getInnerBottom() - 150);
		closeButton.setAlignment(Alignment.CENTER);
		closeButton.setSize(250, 90);

		super.layout();
	}

	public void update() {
		warningLabel.setVisible(System.currentTimeMillis() / 400 % 2 == 0);
	}
}
