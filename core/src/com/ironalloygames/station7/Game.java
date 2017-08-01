package com.ironalloygames.station7;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.ironalloygames.station7.actor.Entity;
import com.ironalloygames.station7.gui.DiagnosticConsoleGUI;
import com.ironalloygames.station7.gui.GuiUtil;
import com.ironalloygames.station7.gui.MasterConsoleGUI;
import com.ironalloygames.station7.gui.PlayerGUI;
import com.ironalloygames.station7.gui.RobotGUI;
import com.ironalloygames.station7.gui.SecurityConsoleGUI;
import com.ironalloygames.station7.gui.TitleGUI;
import com.ironalloygames.station7.gui.WinGUI;

public class Game implements ApplicationListener, InputProcessor, ContactListener {

	public static final int CAMERA_VIEW_HEIGHT = 12;

	public static final int CAMERA_VIEW_WIDTH = 16;

	public static Game s;
	SpriteBatch batch;

	Matrix4 cameraMatrix;

	public DiagnosticConsoleGUI diagnosticConsoleGUI;

	public Stage gui;

	public MasterConsoleGUI masterConsoleGUI;

	long msPassed;

	int mx, my;
	public PlayerGUI playerGUI;
	public RobotGUI robotGUI;
	public SecurityConsoleGUI securityConsoleGUI;
	public GameState state;
	public TitleGUI titleGUI;
	boolean touchDown = false;

	public WinGUI winGUI;

	public World world;

	@Override
	public void beginContact(Contact contact) {

		Object uda = contact.getFixtureA().getBody().getUserData();
		Object udb = contact.getFixtureB().getBody().getUserData();

		if (uda instanceof Entity && udb instanceof Entity) {
			((Entity) uda).collidedWith((Entity) udb);
			((Entity) udb).collidedWith((Entity) uda);
		}
	}

	@Override
	public void create() {
		Sprites.load();
		Sounds.load();

		cameraMatrix = new Matrix4();
		cameraMatrix.setToOrtho2D(0, 0, 16, 12);

		batch = new SpriteBatch();

		world = new World(new Vector2(0, 0), false);
		world.setContactListener(this);

		s = this;

		try {
			/*
			 * ObjectInputStream ois = new
			 * ObjectInputStream(Gdx.files.internal("world.dat").read()); state
			 * = (GameState) ois.readObject(); ois.close();
			 */

			Json j = new Json();
			state = j.fromJson(GameState.class, Gdx.files.internal("world.json"));

			state.postDeserialize();

			/*
			 * Json j = new Json(); Writer fow = new OutputStreamWriter(new
			 * FileOutputStream("world.json")); j.toJson(state, fow);
			 * fow.close();
			 */
		} catch (Exception e) {
			throw new RuntimeException("World loading failed: ", e);
		}

		GuiUtil.init();

		gui = new Stage();

		diagnosticConsoleGUI = new DiagnosticConsoleGUI();
		gui.addActor(diagnosticConsoleGUI);

		securityConsoleGUI = new SecurityConsoleGUI();
		gui.addActor(securityConsoleGUI);

		playerGUI = new PlayerGUI();
		gui.addActor(playerGUI);

		robotGUI = new RobotGUI();
		gui.addActor(robotGUI);

		winGUI = new WinGUI();
		gui.addActor(winGUI);

		masterConsoleGUI = new MasterConsoleGUI();
		gui.addActor(masterConsoleGUI);

		titleGUI = new TitleGUI();
		gui.addActor(titleGUI);

		playerGUI.addMessage("You awaken in the depths of Station 7.");

		state.gameStart();

		resume();

		msPassed = System.currentTimeMillis();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public boolean keyDown(int keycode) {

		// if(keycode == Keys.SPACE)
		// Game.s.state.player.setPosition(Game.s.state.player.getPosition().add(MathUtils.cos(Game.s.state.player.getVisualAngle())*2,
		// MathUtils.sin(Game.s.state.player.getVisualAngle())*2));

		if (keycode == Keys.ESCAPE)
			Gdx.app.exit();

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		mx = x;
		my = y;
		return false;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void render() {
		// batch.setProjectionMatrix(cameraMatrix);

		// batch.begin();

		state.render();

		// batch.end();

		diagnosticConsoleGUI.update();
		securityConsoleGUI.update();
		masterConsoleGUI.update();

		gui.act();
		gui.draw();

		while (msPassed < System.currentTimeMillis()) {
			if (touchDown)
				state.conObj.setDestination(state.screenToReal(mx, my));
			state.update();
			world.step(0.016f, 2, 2);
			msPassed += 16;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		InputMultiplexer im = new InputMultiplexer();
		im.addProcessor(gui);
		im.addProcessor(this);

		Gdx.input.setInputProcessor(im);
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {

		titleGUI.setVisible(false);

		mx = x;
		my = y;

		// System.out.println(state.screenToReal(x, y));
		// state.conObj.setDestination(state.screenToReal(x, y));

		touchDown = true;

		if (winGUI.isVisible())
			Gdx.app.exit();

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// if(playerGUI.isVisible() || robotGUI.isVisible())
		// state.conObj.setDestination(state.screenToReal(x, y));
		mx = x;
		my = y;
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		touchDown = false;
		return false;
	}

}
