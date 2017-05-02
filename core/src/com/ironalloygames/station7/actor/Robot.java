package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Sprites;

public class Robot extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1773485594305633699L;
	
	boolean alive = true;
	
	public Robot()
	{
	}

	@Override
	protected Shape getShape() {
		PolygonShape rs = new PolygonShape();
		rs.setAsBox(0.4f, 0.4f);
		return rs;
	}

	@Override
	protected float getDensity() {
		return 20;
	}

	@Override
	protected boolean isSensor() {
		return false;
	}

	@Override
	protected BodyType getBodyType() {
		return BodyType.DynamicBody;
	}

	@Override
	protected short getCollisionGroupIndex() {
		return -500;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Sprites.robot[(int)(System.currentTimeMillis() / 200 % 2)], getPosition().x - 0.5f, getPosition().y - 0.5f, 0.5f, 0.5f, 1, 1, 0.8f, 0.8f, getAngle() - 90);
		super.render(batch);
	}

	@Override
	public void update() {
		applyForwardImpulse(400);
		super.update();
	}

	@Override
	protected float dragFactor() {
		return 0.95f;
	}
	
	public void destroy()
	{
		alive = false;
	}

	@Override
	public boolean keep() {
		return alive;
	}
}
