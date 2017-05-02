package com.ironalloygames.station7.gui;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import java.util.List;
import com.ironalloygames.station7.Game;
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

public class WinGUI extends Widget {
	
	public WinGUI()
	{
		this.setVisible(false);
	}
	
	@Override
	protected void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		super.layout();
	}
}
