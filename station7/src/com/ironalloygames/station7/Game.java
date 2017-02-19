package com.ironalloygames.station7;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.twl.TWL;
import com.ironalloygames.station7.actor.Entity;
import com.ironalloygames.station7.gui.*;

import de.matthiasmann.twl.Widget;

public class Game implements ApplicationListener, InputProcessor, ContactListener {

	SpriteBatch batch;
	
	Matrix4 cameraMatrix;
	
	public static final int CAMERA_VIEW_WIDTH = 16;
	public static final int CAMERA_VIEW_HEIGHT = 12;
	
	public static Game s;
	
	public World world;
	
	public GameState state;
	
	long msPassed;
	
	TWL gui;
	
	Widget guiRoot;
	
	public DiagnosticConsoleGUI diagnosticConsoleGUI;
	public SecurityConsoleGUI securityConsoleGUI;
	public PlayerGUI playerGUI;
	public RobotGUI robotGUI;
	public WinGUI winGUI;
	public TitleGUI titleGUI;
	public MasterConsoleGUI masterConsoleGUI;
	
	boolean touchDown = false;
	
	int mx,my;
	
	@Override
	public boolean keyDown(int keycode) {
		
		//if(keycode == Keys.SPACE) Game.s.state.player.setPosition(Game.s.state.player.getPosition().add(MathUtils.cos(Game.s.state.player.getVisualAngle())*2, MathUtils.sin(Game.s.state.player.getVisualAngle())*2));
		
		if(keycode == Keys.ESCAPE) Gdx.app.exit();
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		titleGUI.setVisible(false);
		
		mx = x;
		my = y;
		
		//System.out.println(state.screenToReal(x, y));
		//state.conObj.setDestination(state.screenToReal(x, y));
		
		touchDown = true;
		
		if(winGUI.isVisible()) Gdx.app.exit();
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		touchDown = false;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		//if(playerGUI.isVisible() || robotGUI.isVisible()) state.conObj.setDestination(state.screenToReal(x, y));
		mx = x;
		my = y;
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		mx = x;
		my = y;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void create() {
		Sprites.load();
		Sounds.load();
		
		cameraMatrix = new Matrix4();
		cameraMatrix.setToOrtho2D(0, 0, 16, 12);
		
		batch = new SpriteBatch();
		
		world = new World(new Vector2(0,0), false);
		world.setContactListener(this);
		
		s = this;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(Gdx.files.internal("world.dat").read());
			state = (GameState)ois.readObject();
			ois.close();
		} catch(Exception e)
		{
			throw new RuntimeException("World loading failed: " + e);
		}
		
		guiRoot = new Widget();
		gui = new TWL(new SpriteBatch(), "ui/ui.xml", FileType.Internal, guiRoot);
		
		diagnosticConsoleGUI = new DiagnosticConsoleGUI();
		guiRoot.add(diagnosticConsoleGUI);
		
		securityConsoleGUI = new SecurityConsoleGUI();
		guiRoot.add(securityConsoleGUI);
		
		playerGUI = new PlayerGUI();
		guiRoot.add(playerGUI);
		
		robotGUI = new RobotGUI();
		guiRoot.add(robotGUI);
		
		winGUI = new WinGUI();
		guiRoot.add(winGUI);
		
		masterConsoleGUI = new MasterConsoleGUI();
		guiRoot.add(masterConsoleGUI);
		
		titleGUI = new TitleGUI();
		guiRoot.add(titleGUI);
		
		playerGUI.addMessage("You awaken in the depths of Station 7.");
		
		state.gameStart();
		
		resume();
		
		msPassed = System.currentTimeMillis();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		//batch.setProjectionMatrix(cameraMatrix);
		
		//batch.begin();
		
		state.render();
		
		//batch.end();
		
		diagnosticConsoleGUI.update();
		securityConsoleGUI.update();
		masterConsoleGUI.update();
		
		gui.render();
		
		while(msPassed < System.currentTimeMillis())
		{
			if(touchDown) state.conObj.setDestination(state.screenToReal(mx, my));
			state.update();
			world.step(0.016f, 2, 2);
			msPassed += 16;
		}
	}

	@Override
	public void pause() {
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
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginContact(Contact contact) {
		
		Object uda = contact.getFixtureA().getBody().getUserData();
		Object udb = contact.getFixtureB().getBody().getUserData();
		
		if(uda instanceof Entity && udb instanceof Entity)
		{
			((Entity)uda).collidedWith((Entity)udb);
			((Entity)udb).collidedWith((Entity)uda);
		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
