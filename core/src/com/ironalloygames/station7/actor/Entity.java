package com.ironalloygames.station7.actor;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.ironalloygames.station7.Game;

public abstract class Entity extends Actor {

	/**
	 *
	 */
	private static final long serialVersionUID = 7487552954693909412L;

	transient Body body;

	Vector2 pos;

	public Entity() {
		pos = new Vector2(0, 0);
	}

	public void applyForwardImpulse(float power) {
		body.applyForceToCenter(MathUtils.cos(body.getAngle()) * power, MathUtils.sin(body.getAngle()) * power, true);
	}

	public void collidedWith(Entity ent) {

	}

	protected void destroyBody() {
		Game.s.world.destroyBody(body);
		body = null;
	}

	@Override
	public void destroyed() {
		if (body != null)
			destroyBody();
		super.destroyed();
	}

	protected float dragFactor() {
		return 0;
	}

	@Override
	public void gameStart() {
		BodyDef bd = new BodyDef();
		bd.type = getBodyType();
		bd.position.x = pos.x;
		bd.position.y = pos.y;
		bd.fixedRotation = !rotationAllowed();

		body = Game.s.world.createBody(bd);
		body.setUserData(this);

		FixtureDef fd = new FixtureDef();
		fd.shape = getShape();
		fd.density = getDensity();
		fd.isSensor = isSensor();
		fd.filter.groupIndex = getCollisionGroupIndex();
		fd.restitution = getRestitution();

		body.createFixture(fd);

		super.gameStart();
	}

	public float getAngle() {
		if (body != null)
			return body.getAngle() * 180 / MathUtils.PI;
		return 60;
	}

	protected abstract BodyType getBodyType();

	protected short getCollisionGroupIndex() {
		return 0;
	}

	protected abstract float getDensity();

	public Vector2 getPosition() {
		return new Vector2(pos);
	}

	protected float getRestitution() {
		return 0;
	}

	protected abstract Shape getShape();

	protected boolean hasBody() {
		return body != null;
	}

	protected abstract boolean isSensor();

	public void moveTowards(Vector2 trg, float power) {
		if (body == null)
			return;
		Vector2 delta = new Vector2(trg).sub(pos);
		delta.nor().scl(power);

		// System.out.println("IMPULSE: " + delta);

		body.applyForceToCenter(delta, true);
		// body.setTransform(getPosition(), MathUtils.atan2(trg.y - pos.y, trg.x
		// - pos.x));
	}

	protected boolean rotationAllowed() {
		return true;
	}

	public void setPosition(Vector2 pos) {
		if (body != null)
			body.setTransform(pos, body.getAngle());
		this.pos.set(pos);
	}

	public void setPosition(Vector2 pos, float angle) {
		// System.out.println("AN " + angle);
		if (body != null)
			body.setTransform(pos, angle);
		this.pos.set(pos);
	}

	@Override
	public void update() {
		if (body != null) {
			// if(this instanceof DiagnosticLockedDoor) System.out.println(this
			// + " set pos to " + body.getPosition());

			pos.set(body.getPosition());
			body.setLinearVelocity(body.getLinearVelocity().scl(1 - dragFactor()));
		}
		super.update();
	}
}
