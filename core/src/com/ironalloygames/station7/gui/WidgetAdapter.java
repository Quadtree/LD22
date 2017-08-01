package com.ironalloygames.station7.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.ironalloygames.station7.Sprites;

public class WidgetAdapter extends WidgetGroup {

	protected void add(Actor a) {
		this.addActor(a);
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

	protected void setBackgroundImage(Drawable img) {
		Image bk = new Image(img);
		bk.setFillParent(true);

		this.addActor(bk);
	}

	protected void setDialogBackground() {
		setBackgroundImage(new NinePatchDrawable(Sprites.atlas.createPatch("dialog")));
	}
}
