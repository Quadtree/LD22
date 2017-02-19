package com.ironalloygames.station7;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	Sounds(){}
	
	public static Sound combine;
	public static Sound die;
	public static Sound explosion;
	public static Sound message;
	public static Sound pickup;
	public static Sound click;
	public static Sound screen;
	public static Sound door;
	
	public static void load()
	{
		combine = Gdx.audio.newSound(Gdx.files.internal("combine.wav"));
		die = Gdx.audio.newSound(Gdx.files.internal("die.wav"));
		explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
		message = Gdx.audio.newSound(Gdx.files.internal("message.wav"));
		pickup = Gdx.audio.newSound(Gdx.files.internal("pickup.wav"));
		click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
		screen = Gdx.audio.newSound(Gdx.files.internal("screen.wav"));
		door = Gdx.audio.newSound(Gdx.files.internal("door.wav"));
	}
}
