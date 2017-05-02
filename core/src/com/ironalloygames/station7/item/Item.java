package com.ironalloygames.station7.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;
import com.ironalloygames.station7.actor.Entity;
import com.ironalloygames.station7.actor.Player;

public abstract class Item extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2872931941659695577L;
	
	transient boolean onGround;

	@Override
	public void gameStart() {
		onGround = true;
		super.gameStart();
	}

	@Override
	protected Shape getShape() {
		CircleShape cs = new CircleShape();
		cs.setRadius(0.15f);
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
	
	protected abstract Sprite getSprite();

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getSprite(), getPosition().x - 0.25f, getPosition().y - 0.25f, 0.5f, 0.5f);
		super.render(batch);
	}

	@Override
	public void collidedWith(Entity ent) {
		if(ent instanceof Player)
		{
			((Player)ent).addItemToInventory(this);
			onGround = false;
		}
		super.collidedWith(ent);
	}

	@Override
	public boolean keep() {
		if(!onGround)
		{
			destroyBody();
			return false;
		}
		return true;
	}
	
	public abstract String getDescription();
	public abstract String getTitle();
	
	public Item tryCombineWith(Item i)
	{
		return null;
	}
	
	public void use()
	{
	}
}
