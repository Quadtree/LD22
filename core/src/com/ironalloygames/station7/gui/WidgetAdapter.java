package com.ironalloygames.station7.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class WidgetAdapter extends Widget {
	protected void add(Actor a) {
		this.add(a);
	}

	protected float getInnerBottom() {
		return this.getHeight() - 10;
	}

	protected float getInnerHeight() {
		return this.getHeight() - 20;
	}

	protected float getInnerRight() {
		return this.getWidth() - 10;
	}

	protected float getInnerWidth() {
		return this.getWidth() - 20;
	}
}
