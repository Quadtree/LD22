package com.ironalloygames.station7.gui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.actor.Player;
import com.ironalloygames.station7.item.Item;

import de.matthiasmann.twl.textarea.HTMLTextAreaModel;

public class PlayerGUI extends com.badlogic.gdx.scenes.scene2d.ui.Widget {
	class InventoryButtonHandler implements Runnable {
		int ind;

		public InventoryButtonHandler(int ind) {
			super();
			this.ind = ind;
		}

		@Override
		public void run() {
			// System.out.println(ind + " clicked!");
			Sounds.click.play();

			if (ind < items.size()) {
				model.setHtml("<html><body>" + items.get(ind).getDescription() + "</body></html>");

				if (lastItemClick == ind) {
					items.get(ind).use();
				}
			}

			lastItemClick = ind;
		}

	}

	final Button[] inventoryButtons = new Button[3];

	final TextArea itemDesc = new TextArea();

	List<Item> items = new ArrayList<Item>();

	int lastItemClick = -1;

	final Label[] messages = new Label[3];

	final HTMLTextAreaModel model = new HTMLTextAreaModel();

	public PlayerGUI() {
		for (int i = 0; i < messages.length; i++) {
			messages[i] = new Label();
			add(messages[i]);
		}

		for (int i = 0; i < inventoryButtons.length; i++) {
			inventoryButtons[i] = new Button();

			inventoryButtons[i].addCallback(new InventoryButtonHandler(i));

			add(inventoryButtons[i]);
		}

		itemDesc.setModel(model);
		itemDesc.setEnabled(false);
		model.setHtml("<html><body></body></html>");

		add(itemDesc);

		this.setVisible(true);
	}

	public void addMessage(String message) {
		Sounds.message.play();

		for (int i = 0; i < messages.length - 1; ++i) {
			messages[i].setText(messages[i + 1].getText());
		}

		messages[messages.length - 1].setText(message);
	}

	@Override
	protected void layout() {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for (int i = 0; i < inventoryButtons.length; i++) {
			inventoryButtons[i].setPosition(this.getInnerRight() - 140, 20 + i * 110);
			inventoryButtons[i].setSize(128, 128);
		}

		for (int i = 0; i < messages.length; i++) {
			messages[i].setPosition(25, 20 + i * 30);
			// inventoryButtons[i].setSize(128, 128);
		}

		itemDesc.setPosition(this.getInnerRight() - 320, this.getInnerBottom() - 320);
		itemDesc.setSize(280, 280);

		super.layout();
	}

	public void update(Player pl) {
		items = pl.getInventory();
		for (int i = 0; i < inventoryButtons.length; i++) {
			if (i < items.size()) {
				inventoryButtons[i].setTheme(items.get(i).getClass().getSimpleName().toLowerCase());
				inventoryButtons[i].reapplyTheme();
			} else {
				inventoryButtons[i].setTheme("button");
				inventoryButtons[i].reapplyTheme();
			}
		}

		lastItemClick = -1;
	}
}
