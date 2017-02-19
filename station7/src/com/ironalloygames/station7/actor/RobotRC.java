package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Sprites;

public class RobotRC extends Entity implements ControlledObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3402107664045899559L;
	
	Vector2 dest;
	
	boolean alive = true;
	transient boolean moving = false;
	
	public RobotRC()
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
		return 10;
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
		if(moving)
			batch.draw(Sprites.robotRC[(int)(System.currentTimeMillis() / 200 % 2)], getPosition().x - 0.5f, getPosition().y - 0.5f, 0.5f, 0.5f, 1, 1, 0.8f, 0.8f, getAngle() - 90);
		else
			batch.draw(Sprites.robotRC[0], getPosition().x - 0.5f, getPosition().y - 0.5f, 0.5f, 0.5f, 1, 1, 0.8f, 0.8f, getAngle() - 90);
		super.render(batch);
	}

	@Override
	public void update() {
		if(dest == null) dest = new Vector2(pos);
		
		float visualAngle = MathUtils.atan2(dest.y - pos.y, dest.x - pos.x);
		
		if(Math.abs(visualAngle - body.getAngle()) > 0.02f) body.setTransform(body.getPosition(), visualAngle);
		
		if(new Vector2(pos).sub(dest).len() > 0.06f)
		{
			applyForwardImpulse(400);
			moving = true;
		} else {
			moving = false;
		}
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

	@Override
	public void setDestination(Vector2 dest) {
		this.dest = dest;
	}
}
