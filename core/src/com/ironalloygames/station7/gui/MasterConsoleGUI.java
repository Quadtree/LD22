package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;

public class MasterConsoleGUI extends WidgetAdapter {
	final TextButton closeButton = GuiUtil.createButton("", null);
	final Label infoText = GuiUtil.createLabel("");
	final TextButton reactivateButton = GuiUtil.createButton("", null);
	final Label warningLabel = GuiUtil.createLabel("");

	public MasterConsoleGUI() {
		setDialogBackground();

		warningLabel.setText("!!! CODE RED !!!");
		add(warningLabel);

		infoText.setText("Main Core Status: Critical\n\nOperator attention needed. Power output negligible due to instability. Safety systems offline.");

		add(infoText);

		reactivateButton.setText("Reactivate Safety Systems");
		add(reactivateButton);

		reactivateButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				infoText.setText(
						"Main Core Status: Critical\n\nOperator attention needed. Power output negligible due to instability. Safety systems offline.\n\nCommand acknowledged. Access denied. This terminal cannot override a command from a Director. Use the manual on-site override button.");
				Sounds.click.play();
			}
		});

		closeButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				MasterConsoleGUI.this.setVisible(false);
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

		infoText.setPosition(100, 200);
		infoText.setSize(getInnerWidth() - 200, 380);

		reactivateButton.setPosition(450, 500);
		reactivateButton.align(Align.center);
		reactivateButton.setSize(350, 90);

		closeButton.setPosition(this.getInnerRight() - 300, this.getInnerBottom() - 150);
		closeButton.align(Align.center);
		closeButton.setSize(250, 90);

		super.layout();
	}

	public void update() {
		warningLabel.setVisible(System.currentTimeMillis() / 400 % 2 == 0);

		if (Game.s.state.tramOn) {
			infoText.setText("Main Core Status: Nominal\n\nHave a nice day!");
			reactivateButton.setVisible(false);
		}
	}
}
