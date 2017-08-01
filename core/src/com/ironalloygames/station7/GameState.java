package com.ironalloygames.station7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.ironalloygames.station7.actor.Actor;
import com.ironalloygames.station7.actor.ControlledObject;
import com.ironalloygames.station7.actor.Entity;
import com.ironalloygames.station7.actor.Player;

public class GameState implements Serializable {

	public static boolean[] PASSABLE = { true, false, false, false, false, false, false, false, false, false, true, false };

	/**
	 *
	 */
	private static final long serialVersionUID = 6671612488010585643L;

	public static final int WORLD_HEIGHT = 400;

	public static final int WORLD_WIDTH = 128;

	public List<Actor> actors;

	transient List<Actor> addActorQueue;
	transient SpriteBatch batch;

	Body body;

	transient boolean bridgesUp;
	transient Matrix4 cameraMat;

	transient float cameraViewWidth, cameraViewHeight, camX, camY;

	public transient ControlledObject conObj;

	public transient Player player;

	public byte[][] terrain;

	public transient boolean tramOn = false;

	public GameState() {
		terrain = new byte[WORLD_WIDTH][WORLD_HEIGHT];
		actors = new ArrayList<Actor>();
	}

	public void addActor(Actor a) {
		if (addActorQueue == null)
			addActorQueue = new ArrayList<Actor>();
		addActorQueue.add(a);
	}

	public void gameStart() {
		generateCollisionMesh();

		bridgesUp = false;

		for (Actor a : actors) {
			if (a instanceof Player)
				conObj = (ControlledObject) a;
			a.gameStart();
		}
	}

	private void generateCollisionMesh() {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;

		body = Game.s.world.createBody(bd);

		for (int x = 0; x < WORLD_WIDTH; ++x) {
			for (int y = 0; y < WORLD_WIDTH; ++y) {
				if (!isPassable(x, y) && (isPassable(x - 1, y) || isPassable(x + 1, y) || isPassable(x, y - 1) || isPassable(x, y + 1))) {
					FixtureDef fd = new FixtureDef();

					PolygonShape ps = new PolygonShape();
					if (terrain[x][y] == 4)
						ps.set(new Vector2[] { new Vector2(x + 1, y), new Vector2(x, y + 1), new Vector2(x, y) });
					else if (terrain[x][y] == 6)
						ps.set(new Vector2[] { new Vector2(x + 1, y), new Vector2(x + 1, y + 1), new Vector2(x, y) });
					else if (terrain[x][y] == 8)
						ps.set(new Vector2[] { new Vector2(x, y), new Vector2(x + 1, y + 1), new Vector2(x, y + 1) });
					else if (terrain[x][y] == 9)
						ps.set(new Vector2[] { new Vector2(x + 1, y), new Vector2(x + 1, y + 1), new Vector2(x, y + 1) });
					else
						ps.setAsBox(0.5f, 0.5f, new Vector2(x + 0.5f, y + 0.5f), 0);

					fd.shape = ps;

					body.createFixture(fd);
				}
			}
		}
	}

	public boolean isPassable(int x, int y) {
		if (x < 0 || y < 0 || x >= WORLD_WIDTH || y >= WORLD_HEIGHT)
			return false;
		return PASSABLE[terrain[x][y]];
	}

	public void postDeserialize() {
		// ois.defaultReadObject();

		bridgesUp = true;

		if (terrain[0].length != WORLD_HEIGHT) {
			byte[][] ot = terrain;

			terrain = new byte[WORLD_WIDTH][WORLD_HEIGHT];

			System.out.println("Re-sizing world from " + ot.length + "x" + ot[0].length + " to " + terrain.length + "x" + terrain[0].length);

			for (int y = 0; y < ot[0].length; ++y) {
				for (int x = 0; x < ot.length; ++x) {
					terrain[x][y] = ot[x][y];
				}
			}
		}
	}

	public void raiseBridges() {
		/*
		 * for(int x=0;x<WORLD_WIDTH;++x) { for(int y=0;y<WORLD_WIDTH;++y) {
		 * if(terrain[x][y] == 2) { for(Fixture f : body.getFixtureList()) {
		 * if(f.testPoint(x+0.5f, y+0.5f)) { body.destroyFixture(f); break; } }
		 * } } }
		 */

		PASSABLE[2] = true;

		Game.s.world.destroyBody(body);
		body = null;

		generateCollisionMesh();

		bridgesUp = true;
	}

	public void render() {

		// float aspectMod = 1;
		// System.out.println(aspectMod);

		if (cameraMat == null)
			setCamera(0, 0, 16, 12);
		if (batch == null)
			batch = new SpriteBatch();

		batch.setProjectionMatrix(cameraMat);
		batch.begin();

		for (int x = (int) (camX - cameraViewWidth / 2 - 1); x < (int) (camX + cameraViewWidth / 2 + 1); ++x) {
			for (int y = (int) (camY - cameraViewHeight / 2 - 1); y < (int) (camY + cameraViewHeight / 2 + 1); ++y) {
				if (x < 0 || y < 0)
					batch.draw(Sprites.ground[1], x, y, 1, 1);
				else if (terrain[x][y] != 2 || bridgesUp)
					batch.draw(Sprites.ground[terrain[x][y]], x, y, 1, 1);
				else
					batch.draw(Sprites.ground[3], x, y, 1, 1);
			}
		}

		Collections.sort(actors);

		float clip = (float) (Math.pow(cameraViewWidth / 2, 2) + Math.pow(cameraViewHeight / 2, 2));

		for (Actor a : actors) {
			if (a instanceof Entity && ((Entity) a).getPosition().sub(camX, camY).len2() > clip)
				continue;
			a.render(batch);
		}

		batch.end();
	}

	public Vector2 screenToReal(int sx, int sy) {
		float tx = sx / (float) Gdx.graphics.getWidth();
		float ty = 1 - (sy / (float) Gdx.graphics.getHeight());

		tx *= cameraViewWidth;
		ty *= cameraViewHeight;

		tx += camX;
		ty += camY;

		tx -= cameraViewWidth / 2;
		ty -= cameraViewHeight / 2;

		return new Vector2(tx, ty);
	}

	public void setCamera(float x, float y, float viewWidth, float viewHeight) {
		cameraViewWidth = viewWidth;
		cameraViewHeight = viewHeight;
		camX = x;
		camY = y;
		if (cameraMat == null)
			cameraMat = new Matrix4();

		cameraMat.setToOrtho2D(x - viewWidth / 2, y - viewHeight / 2, viewWidth, viewHeight);
	}

	public void update() {
		if (addActorQueue == null)
			addActorQueue = new ArrayList<Actor>();

		actors.addAll(addActorQueue);
		addActorQueue.clear();

		for (int i = 0; i < actors.size(); ++i) {
			if (actors.get(i).keep())
				actors.get(i).update();
			else {
				actors.get(i).destroyed();
				actors.remove(i--);
			}

			if (actors.get(i) instanceof Player)
				player = (Player) actors.get(i);
		}

		// System.out.println("Total actors: " + actors.size());

		float aspectMod = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight() / (1024.f / 768.f);

		Game.s.state.setCamera(conObj.getPosition().x, conObj.getPosition().y, Game.CAMERA_VIEW_WIDTH * aspectMod, Game.CAMERA_VIEW_HEIGHT);
	}
}
