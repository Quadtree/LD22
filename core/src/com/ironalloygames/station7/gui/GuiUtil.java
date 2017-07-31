package com.ironalloygames.station7.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip.TextTooltipStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;

public class GuiUtil {
	static TextButtonStyle defaultButtonStyle;
	static WindowStyle defaultDialogStyle;
	static LabelStyle defaultLabelStyle;
	static TextTooltipStyle defaultTooltipStyle;
	static BitmapFont mainFont;

	public static Button addButtonEvent(Button b, ChangeListener list) {
		b.addListener(list);
		return b;
	}

	public static Button createButton(String text, ChangeListener list) {
		TextButton ret = new TextButton(text, defaultButtonStyle);
		ret.addListener(list);
		ret.getCells().get(0).padLeft(5).padRight(5).padTop(0).padBottom(0);
		return ret;
	}

	public static Dialog createDialog(String text, Button... buttons) {
		final Dialog wnd = new Dialog("", defaultDialogStyle);
		// wnd.setSize(300, 300);

		wnd.getContentTable().add(GuiUtil.createLabel(text)).pad(8);
		for (Button bt : buttons)
			wnd.button(bt).pad(8);
		wnd.pack();
		wnd.setPosition(Gdx.graphics.getWidth() / 2.f - (int) wnd.getWidth() / 2, Gdx.graphics.getHeight() / 2.f - (int) wnd.getHeight() / 2);
		Game.s.gui.addActor(wnd);

		return wnd;
	}

	public static Label createLabel(String text) {
		Label ret = new Label(text, defaultLabelStyle);
		return ret;
	}

	public static Label createLabel(String text, String tooltip) {
		Label ret = new Label(text, defaultLabelStyle);

		ret.addListener(new TextTooltip(tooltip, defaultTooltipStyle));

		return ret;
	}

	public static Dialog getParentDialog(Actor actor) {
		Actor a = actor;
		while (a != null && !(a instanceof Dialog)) {
			a = a.getParent();
		}

		if (a instanceof Dialog)
			return (Dialog) a;
		return null;
	}

	public static void init() {
		TextureAtlas atlas = Sprites.atlas;

		mainFont = new BitmapFont();
		defaultLabelStyle = new LabelStyle(mainFont, Color.WHITE);
		defaultDialogStyle = new WindowStyle(mainFont, Color.WHITE, new NinePatchDrawable(atlas.createPatch("dialog1")));
		defaultButtonStyle = new TextButtonStyle(new NinePatchDrawable(atlas.createPatch("dialog3")), new NinePatchDrawable(atlas.createPatch("dialog2")), new NinePatchDrawable(atlas.createPatch("dialog3")), mainFont);
		defaultTooltipStyle = new TextTooltipStyle(defaultLabelStyle, new NinePatchDrawable(atlas.createPatch("dialog1")));
	}
}
