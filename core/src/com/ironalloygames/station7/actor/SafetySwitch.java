package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;

public class SafetySwitch extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3668056635004768046L;
	transient boolean pressed = false;
	
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
		if(ent instanceof RobotRC || ent instanceof Player || ent instanceof Robot)
		{
			Game.s.state.tramOn = true;
			pressed = true;
		}
			
		super.collidedWith(ent);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		if(!pressed)
			batch.draw(Sprites.safetyUnpressed, pos.x - 0.4f, pos.y - 0.4f, 0.8f, 0.8f);
		else
			batch.draw(Sprites.safetyPressed, pos.x - 0.4f, pos.y - 0.4f, 0.8f, 0.8f);
		
		super.render(batch);
	}
}
