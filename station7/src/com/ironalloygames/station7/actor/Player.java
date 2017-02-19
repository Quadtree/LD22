package com.ironalloygames.station7.actor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sounds;
import com.ironalloygames.station7.Sprites;
import com.ironalloygames.station7.item.Item;

public class Player extends Entity implements ControlledObject {
	
	transient Vector2 destination;
	
	float visualAngle;
	
	transient int respawn = 0;
	
	transient List<Item> inventory;
	
	transient Vector2 startPos;
	
	transient boolean moving = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2879850102057724743L;

	@Override
	public void render(SpriteBatch batch) {
		if(respawn == 0)
		{
			Sprite sp = Sprites.player[0];
			if(moving) sp = Sprites.player[(int) (System.currentTimeMillis() / 150 % 4)];
			batch.draw(sp, getPosition().x - 0.5f, getPosition().y - 0.5f, 0.5f, 0.5f, 1, 1, 0.6f, 0.6f, visualAngle*180 / MathUtils.PI);
			super.render(batch);
		}
	}
	
	public List<Item> getInventory()
	{
		if(inventory == null) inventory = new ArrayList<Item>();
		return inventory;
	}
	
	public void addItemToInventory(Item i)
	{
		Game.s.playerGUI.addMessage("You pick up the the " + i.getTitle().toLowerCase() + ".");
		
		getInventory().add(i);
		
		Item ni=null, itm1=null, itm2=null;
		
		for(Item i1 : inventory)
		{
			for(Item i2 : inventory)
			{
				if(i1 == i2) continue;
				
				Item pni = i1.tryCombineWith(i2);
				if(pni != null)
				{
					ni = pni;
					itm1 = i1;
					itm2 = i2;
				}
			}
		}
		
		if(ni != null)
		{
			//System.out.println(itm1 + " + " + itm2 + " = " + ni);
			
			Game.s.playerGUI.addMessage("You combine the " + itm1.getTitle().toLowerCase() + " and the " + itm2.getTitle().toLowerCase() + " to form a " + ni.getTitle().toLowerCase() + ".");
			
			Sounds.combine.play();
			
			getInventory().remove(itm1);
			getInventory().remove(itm2);
			getInventory().add(ni);
		} else {
			Sounds.pickup.play();
		}
		
		Game.s.playerGUI.update(this);
	}

	@Override
	protected Shape getShape() {
		CircleShape cs = new CircleShape();
		cs.setRadius(0.25f);
		return cs;
	}

	@Override
	protected float getDensity() {
		return 1.f;
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
	public void update() {
		if(respawn == 0)
		{
			if(getPosition().sub(destination).len() > 0.06f)
			{
				visualAngle = MathUtils.atan2(destination.y - pos.y, destination.x - pos.x);
				moveTowards(destination, 40);
				moving = true;
			} else {
				moving = false;
			}
			
			super.update();
		} else {
			if(body != null) destroyBody();
			
			if(--respawn == 0)
			{
				System.out.println("Respawning player to " + startPos);
				setPosition(startPos);
				destination = pos;
				super.gameStart();
			}
		}
	}

	@Override
	public void setDestination(Vector2 dest) {
		destination = dest;
	}

	@Override
	public void gameStart() {
		destination = pos;
		startPos = new Vector2(pos);
		super.gameStart();
	}

	@Override
	protected boolean rotationAllowed() {
		return false;
	}

	@Override
	protected float dragFactor() {
		return 0.95f;
	}

	@Override
	public int getRenderPriority() {
		return -100;
	}
	
	@Override
	protected short getCollisionGroupIndex() {
		return -500;
	}

	public float getVisualAngle()
	{
		return visualAngle;
	}

	@Override
	public void collidedWith(Entity ent) {
		if(ent instanceof Fire)
		{
			Sounds.die.play();
			respawn = 60;
		}
		super.collidedWith(ent);
	}
}
