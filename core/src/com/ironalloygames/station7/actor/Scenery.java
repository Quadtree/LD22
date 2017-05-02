package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;

public abstract class Scenery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8864271971684997557L;

	@Override
	protected Shape getShape() {
		CircleShape cs = new CircleShape();
		cs.setRadius(getSize() / 2);
		return cs;
	}

	@Override
	protected float getDensity() {
		return 0;
	}

	@Override
	protected boolean isSensor() {
		return false;
	}

	@Override
	protected BodyType getBodyType() {
		return BodyType.StaticBody;
	}
	
	protected abstract Sprite getSprite();
	protected abstract float getSize();

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getSprite(), pos.x - getSize()/2, pos.y - getSize()/2, getSize(), getSize());
		super.render(batch);
	}

	@Override
	public void collidedWith(Entity ent) {
		if(ent instanceof Player && getMessage() != null) Game.s.playerGUI.addMessage(getMessage());
		super.collidedWith(ent);
	}
	
	protected String getMessage(){ return null; }
	protected boolean onSquare(){ return false; }
	
	@Override
	public void setPosition(Vector2 pos) {
		if(onSquare())
		{
			pos.x = (int)(pos.x - 0.5f) + 0.5f;
			pos.y = (int)(pos.y - 0.5f) + 0.5f;
		}
		
		super.setPosition(pos);
	}
}
