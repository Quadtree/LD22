package com.ironalloygames.station7.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.Sprites;

public class BigFlamethrower extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = -6657445456146131531L;

	transient RevoluteJoint joint;

	@Override
	public void gameStart() {
		super.gameStart();

		this.setPosition(pos, MathUtils.PI + 0.08f);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.x = pos.x;
		bd.position.y = pos.y;

		Body b2 = Game.s.world.createBody(bd);

		RevoluteJointDef jd = new RevoluteJointDef();
		jd.initialize(body, b2, pos);
		jd.maxMotorTorque = 100;
		jd.motorSpeed = 0;

		joint = (RevoluteJoint) Game.s.world.createJoint(jd);
		joint.enableMotor(true);
	}

	@Override
	protected BodyType getBodyType() {
		return BodyType.DynamicBody;
	}

	@Override
	protected float getDensity() {
		return 150;
	}

	@Override
	protected Shape getShape() {
		PolygonShape rs = new PolygonShape();
		rs.setAsBox(1.9f / 2, 0.5f / 2);
		return rs;
	}

	@Override
	protected boolean isSensor() {
		return false;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Sprites.bigFlamethrower, getPosition().x - 0.5f, getPosition().y - 0.5f, 0.5f, 0.5f, 1, 1, 1.9f, 0.5f, getAngle());
		super.render(batch);
	}

	@Override
	public void update() {
		// System.out.println(getAngle());
		if (getAngle() > 180) {
			// System.out.println("NEG PWR");
			joint.setMotorSpeed(100);
		} else {
			// System.out.println("POS PWR");
			joint.setMotorSpeed(-100);
		}

		if (Math.random() < 0.5)
			Game.s.state.addActor((new Fire(new Vector2(pos).add(MathUtils.cos(body.getAngle()), MathUtils.sin(body.getAngle())), body.getAngle())));

		super.update();
	}
}
