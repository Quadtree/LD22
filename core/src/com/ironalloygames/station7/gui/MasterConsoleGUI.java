package com.ironalloygames.station7.gui;

import javax.swing.GroupLayout.Alignment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;

import de.matthiasmann.twl.textarea.HTMLTextAreaModel;

public class MasterConsoleGUI extends Widget {
	final Button closeButton = new Button();
	final TextArea infoText = new TextArea();
	final HTMLTextAreaModel model = new HTMLTextAreaModel();
	final Button reactivateButton = new Button();
	final Label warningLabel = new Label();

	public MasterConsoleGUI() {
		warningLabel.setText("!!! CODE RED !!!");
		add(warningLabel);

		infoText.setModel(model);
		model.setHtml("<html><body>Main Core Status: Critical<br/><br/>Operator attention needed. Power output negligible due to instability. Safety systems offline.</body></html>");

		add(infoText);

		reactivateButton.setText("Reactivate Safety Systems");
		add(reactivateButton);

		reactivateButton.addCallback(new Runnable() {

			@Override
			public void run() {
				model.setHtml(
						"<html><body>Main Core Status: Critical<br/><br/>Operator attention needed. Power output negligible due to instability. Safety systems offline.<br/><br/>Command acknowledged. Access denied. This terminal cannot override a command from a Director. Use the manual on-site override button.</body></html>");
				Sounds.click.play();
			}
		});

		closeButton.addCallback(new Runnable() {

			@Override
			public void run() {
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
	protected void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		warningLabel.setPosition(100, 100);

		infoText.setPosition(100, 200);
		infoText.setSize(getInnerWidth() - 200, 380);

		reactivateButton.setPosition(450, 500);
		reactivateButton.setAlignment(Alignment.CENTER);
		reactivateButton.setSize(350, 90);

		closeButton.setPosition(this.getInnerRight() - 300, this.getInnerBottom() - 150);
		closeButton.setAlignment(Alignment.CENTER);
		closeButton.setSize(250, 90);

		super.layout();
	}

	public void update() {
		warningLabel.setVisible(System.currentTimeMillis() / 400 % 2 == 0);

		if (Game.s.state.tramOn) {
			model.setHtml("<html><body>Main Core Status: Nominal<br/><br/>Have a nice day!</body></html>");
			reactivateButton.setVisible(false);
		}
	}
}
