package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;

public class DestructibleWall extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5395116662805438706L;
	
	transient int damage = 0;

	@Override
	protected Shape getShape() {
		PolygonShape rs = new PolygonShape();
		rs.setAsBox(0.5f, 0.5f);
		return rs;
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

	@Override
	public void setPosition(Vector2 pos) {
		pos.x = (int)(pos.x - 0.5f) + 0.5f;
		pos.y = (int)(pos.y - 0.5f) + 0.5f;
		
		super.setPosition(pos);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Sprites.destWall, pos.x - 0.5f, pos.y - 0.5f, 1, 1);
		super.render(batch);
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void collidedWith(Entity ent) {
		if(ent instanceof Fire) damage++;
		super.collidedWith(ent);
	}

	@Override
	public boolean keep() {
		return damage < 4;
	}
}
