package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.actor.Actor;
import com.ironalloygames.station7.actor.FlameNozzle;

public class SecurityConsoleGUI extends WidgetAdapter {
	final TextButton bridgesButton = GuiUtil.createButton("", null);
	final Label bridgesLabel = GuiUtil.createLabel("");
	final TextButton closeButton = GuiUtil.createButton("", null);
	final TextButton pyroButton = GuiUtil.createButton("", null);
	final Label pyroLabel = GuiUtil.createLabel("");
	final Label warningLabel = GuiUtil.createLabel("");

	public SecurityConsoleGUI() {
		setDialogBackground();

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

		pyroButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				for (Actor a : Game.s.state.actors) {
					if (a instanceof FlameNozzle) {
						((FlameNozzle) a).disabled = true;
						pyroLabel.setText("Pyrodefense OFFLINE");
					}
				}
				Sounds.click.play();
			}
		});

		closeButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				SecurityConsoleGUI.this.setVisible(false);
				Game.s.playerGUI.setVisible(true);
				Sounds.click.play();
			}
		});

		bridgesButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
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
	public void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		warningLabel.setPosition(100, 100);

		pyroLabel.setPosition(100, 300);

		pyroButton.setPosition(450, 250);
		pyroButton.align(Align.center);
		pyroButton.setSize(250, 90);

		bridgesLabel.setPosition(100, 400);

		bridgesButton.setPosition(450, 350);
		bridgesButton.align(Align.center);
		bridgesButton.setSize(250, 90);

		closeButton.setPosition(this.getInnerRight() - 300, this.getInnerBottom() - 150);
		closeButton.align(Align.center);
		closeButton.setSize(250, 90);

		super.layout();
	}

	public void update() {
		warningLabel.setVisible(System.currentTimeMillis() / 400 % 2 == 0);
	}
}
