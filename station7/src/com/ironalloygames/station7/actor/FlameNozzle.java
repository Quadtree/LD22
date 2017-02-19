package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;

public class FlameNozzle extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2070221945847498867L;
	
	public transient boolean disabled;

	@Override
	protected Shape getShape() {
		PolygonShape rs = new PolygonShape();
		rs.setAsBox(0.25f, 0.25f);
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
	public void render(SpriteBatch batch) {
		batch.draw(Sprites.flameNozzle, pos.x - 0.25f, pos.y - 0.25f, 0.5f, 0.5f);
		super.render(batch);
	}

	@Override
	public void update() {
		
		if(!disabled && Math.random() < 0.5) Game.s.state.addActor((new Fire(pos, 0)));
		
		super.update();
	}

	@Override
	protected short getCollisionGroupIndex() {
		return -150;
	}
}
