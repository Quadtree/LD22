package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Sprites;

public abstract class Console extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1329182951844095261L;

	@Override
	protected Shape getShape() {
		CircleShape cs = new CircleShape();
		cs.setRadius(0.25f);
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

	protected abstract void interactedWith();

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Sprites.console, pos.x - 0.5f, pos.y - 0.5f, 1, 1);
		super.render(batch);
	}

	@Override
	public void collidedWith(Entity ent) {
		if(ent instanceof Player) this.interactedWith();
		super.collidedWith(ent);
	}
}
