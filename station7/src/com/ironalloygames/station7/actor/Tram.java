package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;

public class Tram extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7294593429710499247L;

	@Override
	protected Shape getShape() {
		CircleShape cs = new CircleShape();
		cs.setRadius(0.4f);
		return cs;
	}

	@Override
	protected float getDensity() {
		return 0;
	}

	@Override
	protected boolean isSensor() {
		return true;
	}

	@Override
	protected BodyType getBodyType() {
		return BodyType.StaticBody;
	}

	@Override
	public void collidedWith(Entity ent) {
		if(Game.s.state.tramOn)
		{
			Game.s.winGUI.setVisible(true);
		} else {
			Game.s.playerGUI.addMessage("The tram isn't working. It needs power from the main core.");
		}
			
		super.collidedWith(ent);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Sprites.tramEntrance, pos.x - 0.5f, pos.y - 0.5f, 1, 1);
		
		super.render(batch);
	}

	@Override
	public void setPosition(Vector2 pos) {
		pos.x = (int)(pos.x - 0.5f) + 0.5f;
		pos.y = (int)(pos.y - 0.5f) + 0.5f;
		
		super.setPosition(pos);
	}
}
