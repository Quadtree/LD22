package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.Sprites;

public class ExplodingBarrel extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4999214127185680007L;
	
	transient boolean exploding = false;
	transient boolean exploded = false;
	
	@Override
	protected Shape getShape() {
		CircleShape cs = new CircleShape();
		cs.setRadius(0.25f);
		return cs;
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
	public void render(SpriteBatch batch) {
		batch.draw(Sprites.explodingBarrel, pos.x - 0.25f, pos.y - 0.25f, 0.5f, 0.5f);
		super.render(batch);
	}

	@Override
	public void collidedWith(Entity ent) {
		if(ent instanceof Fire)
		{
			exploding = true;
		}
		
		super.collidedWith(ent);
	}

	@Override
	public void update() {
		if(exploding)
		{
			Sounds.explosion.play();
			destroyBody();
			for(int i=0;i<10;i++)
			{
				Game.s.state.addActor((new Fire(pos, (float)(Math.random() * Math.PI * 2), 0.035f)));
			}
			exploded = true;
		}
		super.update();
	}

	@Override
	public boolean keep() {
		return !exploded;
	}

	@Override
	protected float dragFactor() {
		return 0.8f;
	}
}
