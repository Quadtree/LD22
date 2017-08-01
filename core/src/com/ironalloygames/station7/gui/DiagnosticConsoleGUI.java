package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.actor.Actor;
import com.ironalloygames.station7.actor.DiagnosticLockedDoor;

public class DiagnosticConsoleGUI extends WidgetAdapter {
	final TextButton closeButton = GuiUtil.createButton("", null);
	final TextButton disengageButton = GuiUtil.createButton("", null);
	final Label lockdownLabel = GuiUtil.createLabel("");
	final Label warningLabel = GuiUtil.createLabel("");

	public DiagnosticConsoleGUI() {

		setDialogBackground();

		warningLabel.setText("!!! CODE RED !!!");
		add(warningLabel);
		lockdownLabel.setText("Sector lockdown in effect.");
		add(lockdownLabel);

		disengageButton.setText("Disengage");
		add(disengageButton);

		disengageButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
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

		closeButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
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
	public void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		warningLabel.setPosition(100, 100);

		lockdownLabel.setPosition(100, this.getInnerBottom() - 200);

		disengageButton.setSize(250, 90);
		disengageButton.align(Align.center);
		disengageButton.setPosition(450, this.getInnerBottom() - 250);

		closeButton.setSize(250, 90);
		closeButton.align(Align.center);
		closeButton.setPosition(this.getInnerRight() - 300, this.getInnerBottom() - 150);

		super.layout();
	}

	public void update() {
		warningLabel.setVisible(System.currentTimeMillis() / 400 % 2 == 0);
	}
}
