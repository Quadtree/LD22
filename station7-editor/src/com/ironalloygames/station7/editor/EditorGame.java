package com.ironalloygames.station7.editor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ironalloygames.station7.Game;
import com.ironalloygames.station7.GameState;
import com.ironalloygames.station7.Sprites;
import com.ironalloygames.station7.actor.*;
import com.ironalloygames.station7.item.*;

public class EditorGame implements ApplicationListener, InputProcessor{

	SpriteBatch batch;
	
	GameState state;
	
	float camX, camY;
	
	int mvHrz, mvVrt;
	
	long msPassed;
	
	byte terrainBrush;
	boolean painting;
	
	private static final int CAMERA_VIEW_WIDTH = 16*1920/1024;
	private static final int CAMERA_VIEW_HEIGHT = 12*1080/768;
	
	float worldX, worldY;
	
	class TilePos
	{
		public int x;
		public int y;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.D) mvHrz = 1;
		if(keycode == Keys.A) mvHrz = -1;
		if(keycode == Keys.W) mvVrt = 1;
		if(keycode == Keys.S) mvVrt = -1;
		
		if(keycode == Keys.NUM_0) createEntityAtCursor(new Player());
		if(keycode == Keys.U) createEntityAtCursor(new UnlockedDoor());
		if(keycode == Keys.G) createEntityAtCursor(new DiagnosticLockedDoor());
		if(keycode == Keys.N) createEntityAtCursor(new DiagnosticConsole());
		if(keycode == Keys.Z) createEntityAtCursor(new FlameNozzle());
		if(keycode == Keys.C) createEntityAtCursor(new SecurityConsole());
		
		if(keycode == Keys.O) createEntityAtCursor(new ManualLeftHalf());
		if(keycode == Keys.P) createEntityAtCursor(new ManualRightHalf());
		
		if(keycode == Keys.J) createEntityAtCursor(new Joystick());
		if(keycode == Keys.R) createEntityAtCursor(new Radio());
		if(keycode == Keys.M) createEntityAtCursor(new RobotMotor());
		if(keycode == Keys.T) createEntityAtCursor(new RobotTracks());
		
		if(keycode == Keys.B) createEntityAtCursor(new BigFlamethrower());
		
		if(keycode == Keys.E) createEntityAtCursor(new ExplodingBarrel());
		
		if(keycode == Keys.X) createEntityAtCursor(new DestructibleWall());
		
		if(keycode == Keys.L) createEntityAtCursor(new SafetySwitch());
		
		if(keycode == Keys.NUM_7) createEntityAtCursor(new Tram());
		
		if(keycode == Keys.Y) createEntityAtCursor(new Tank());
		if(keycode == Keys.K) createEntityAtCursor(new TankBroken());
		
		if(keycode == Keys.F) createEntityAtCursor(new Footprint());
		
		if(keycode == Keys.NUM_6) createEntityAtCursor(new AirTank());
		
		if(keycode == Keys.H) createEntityAtCursor(new Core());
		
		if(keycode == Keys.NUM_1) createEntityAtCursor(new Bed());
		if(keycode == Keys.NUM_2) createEntityAtCursor(new Cabinets());
		if(keycode == Keys.NUM_3) createEntityAtCursor(new HardwareShelf());
		if(keycode == Keys.NUM_4) createEntityAtCursor(new Device());
		if(keycode == Keys.NUM_5) createEntityAtCursor(new MasterConsole());
		
		if(keycode == Keys.I) createEntityAtCursor(new InertConsole());
		
		if(keycode == Keys.V)
		{
			for(int i=0;i<state.actors.size();++i)
			{
				if(state.actors.get(i) instanceof Radio) state.actors.remove(i--);
			}
		}
		
		if(keycode == Keys.BACKSPACE)
		{
			//System.out.println("Finding something to delete");
			float bestDist = Float.MAX_VALUE;
			Entity closest = null;
			
			for(Actor a : state.actors)
			{
				if(!(a instanceof Entity)) continue;
				
				Entity e = (Entity)a;
				float dist = e.getPosition().sub(new Vector2(worldX, worldY)).len2();
				if(dist < bestDist)
				{
					bestDist = dist;
					closest = e;
				}
			}
			
			if(closest != null)
			{
				System.out.println("Deleting: " + closest);
				state.actors.remove(closest);
			}
		}
		
		return false;
	}
	
	void createEntityAtCursor(Entity a)
	{
		state.actors.add(a);
		a.setPosition(new Vector2(worldX, worldY));
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.D) mvHrz = 0;
		if(keycode == Keys.A) mvHrz = 0;
		if(keycode == Keys.W) mvVrt = 0;
		if(keycode == Keys.S) mvVrt = 0;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public TilePos getTile(int cx, int cy)
	{
		float tx = cx / (float)Gdx.graphics.getWidth();
		float ty = 1 - (cy / (float)Gdx.graphics.getHeight());
		
		tx *= CAMERA_VIEW_WIDTH;
		ty *= CAMERA_VIEW_HEIGHT;
		
		tx += camX;
		ty += camY;
		
		tx -= CAMERA_VIEW_WIDTH / 2;
		ty -= CAMERA_VIEW_HEIGHT / 2;
		
		worldX = tx;
		worldY = ty;
		
		TilePos tp = new TilePos();
		tp.x = (int)tx;
		tp.y = (int)ty;
		
		return tp;
	}
	
	void paintAt(int cx, int cy)
	{
		TilePos tp = getTile(cx,cy);
		if(tp.x < 0 || tp.x >= GameState.WORLD_WIDTH || tp.y < 0 || tp.y >= GameState.WORLD_HEIGHT) return;
		
		state.terrain[tp.x][tp.y] = terrainBrush;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		paintAt(x,y);
		//painting = true;
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		//painting = false;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		paintAt(x,y);
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		getTile(x,y);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		amount /= Math.abs(amount);
		
		terrainBrush += amount;
		
		if(terrainBrush < 0) terrainBrush = (byte)(Sprites.ground.length - 1);
		if(terrainBrush >= Sprites.ground.length) terrainBrush = 0;
		
		return false;
	}

	@Override
	public void create() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../station7-android/assets/world.dat"));
			state = (GameState)ois.readObject();
			ois.close();
		} catch(Exception e)
		{
			state = new GameState();
			System.out.println("Creating new world state, because " + e);
		}
		
		Sprites.load();
		
		msPassed = System.currentTimeMillis();
		
		batch = new SpriteBatch();
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		state.render();
		
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		batch.begin();
		
		batch.draw(Sprites.ground[terrainBrush], 100, 100, 100, 100);
		
		batch.end();
		
		while(msPassed < System.currentTimeMillis())
		{
			camX += mvHrz * 0.3f;
			camY += mvVrt * 0.3f;
			
			state.setCamera(camX, camY, CAMERA_VIEW_WIDTH, CAMERA_VIEW_HEIGHT);
			
			msPassed += 16;
		}
	}

	@Override
	public void pause() {
		try {
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("../station7-android/assets/world.dat"));
			ois.writeObject(state);
			ois.close();
		} catch(Exception e)
		{
			System.out.println("File write failed!");
		}
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
