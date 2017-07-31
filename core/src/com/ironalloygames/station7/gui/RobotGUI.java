package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;

public class RobotGUI extends WidgetAdapter {
	final TextButton exitControlButton = GuiUtil.createButton("", null);

	public RobotGUI() {
		exitControlButton.setText("Exit Robot Control");

		exitControlButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
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
	public void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		exitControlButton.setSize(300, 90);
		exitControlButton.align(Align.center);
		exitControlButton.setPosition(this.getInnerWidth() / 2 - exitControlButton.getWidth() / 2, 50);

		super.layout();
	}
}
