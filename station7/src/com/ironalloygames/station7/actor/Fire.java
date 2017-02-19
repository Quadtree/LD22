package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Sprites;

public class Fire extends Entity {

	private static final int START_VELOCITY = 4;

	/**
	 * 
	 */
	private static final long serialVersionUID = -7366074885430604084L;
	
	transient float life = 0;
	transient float drag = 0;
	
	public Fire(Vector2 start, float angle)
	{
		angle += (float)((Math.random() - 0.5) * 0.2);
		gameStart();
		body.setLinearVelocity(MathUtils.cos(angle) * START_VELOCITY, MathUtils.sin(angle) * START_VELOCITY);
		body.setTransform(start, angle);
	}
	
	public Fire(Vector2 start, float angle, float drag)
	{
		angle += (float)((Math.random() - 0.5) * 0.2);
		gameStart();
		body.setLinearVelocity(MathUtils.cos(angle) * START_VELOCITY, MathUtils.sin(angle) * START_VELOCITY);
		body.setTransform(start, angle);
		this.drag = drag;
	}

	@Override
	protected Shape getShape() {
		CircleShape cs = new CircleShape();
		cs.setRadius(0.15f);
		return cs;
	}

	@Override
	protected float getDensity() {
		return 0.01f;
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
		return -150;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		
		float size = 0.25f + life * 0.3f;
		batch.setColor(1, 1, 1, Math.max(1 - life, 0));
		batch.draw(Sprites.fire, getPosition().x - 0.5f, getPosition().y - 0.5f, 0.5f, 0.5f, 1, 1, size, size, getAngle());
		batch.setColor(Color.WHITE);
		super.render(batch);
	}

	@Override
	public void update() {
		life += 0.013f;
		super.update();
	}

	@Override
	public boolean keep() {
		return life < 1;
	}

	@Override
	protected float getRestitution() {
		return 0.3f;
	}

	@Override
	protected float dragFactor() {
		return drag;
	}
}
