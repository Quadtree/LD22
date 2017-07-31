package com.ironalloygames.station7;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Sprites {
	public static Sprite airTank;

	public static TextureAtlas atlas;
	public static Sprite bed;
	public static Sprite bigFlamethrower;
	public static Sprite bigFlamethrowerBase;
	public static Sprite cabinets;
	public static Sprite console;
	public static Sprite core;
	public static Sprite destWall;
	public static Sprite device;
	public static Sprite doorClosed;

	public static Sprite doorOpen;
	public static Sprite explodingBarrel;
	public static Sprite fire;
	public static Sprite flameNozzle;
	public static Sprite footprint;
	public static Sprite[] ground;
	public static Sprite hardwareShelf;

	public static Sprite joystick;
	public static Sprite manual;

	public static Sprite manualLeftHalf;

	public static Sprite manualRightHalf;

	public static Sprite motor;
	public static Sprite[] player = new Sprite[4];

	public static Sprite radio;

	public static Sprite remote;
	public static Sprite[] robot = new Sprite[2];

	public static Sprite[] robotRC = new Sprite[2];

	public static Sprite safetyPressed;

	public static Sprite safetyUnpressed;

	public static Sprite tank;
	public static Sprite tankBroken;
	public static Sprite tracks;

	public static Sprite tramEntrance;

	public static void load() {
		atlas = new TextureAtlas("main.atlas");

		ground = new Sprite[12];
		ground[0] = atlas.createSprite("ground1");
		ground[1] = atlas.createSprite("wall1");
		ground[2] = atlas.createSprite("bridge");
		ground[3] = atlas.createSprite("blank");

		ground[4] = atlas.createSprite("leftedge1");
		ground[5] = atlas.createSprite("leftedge2");

		ground[6] = atlas.createSprite("rightedge1");
		ground[7] = atlas.createSprite("rightedge2");

		ground[8] = atlas.createSprite("lefttopedge");
		ground[9] = atlas.createSprite("righttopedge");

		ground[10] = atlas.createSprite("cavefloor");
		ground[11] = atlas.createSprite("wallwindow");

		player[0] = atlas.createSprite("player");
		player[1] = atlas.createSprite("player1");
		player[2] = atlas.createSprite("player");
		player[3] = atlas.createSprite("player2");

		doorOpen = atlas.createSprite("dooropen");
		doorClosed = atlas.createSprite("doorclosed");

		console = atlas.createSprite("console");

		fire = atlas.createSprite("fire");
		flameNozzle = atlas.createSprite("flamenozzle");

		manualLeftHalf = atlas.createSprite("man1");
		manualRightHalf = atlas.createSprite("man2");

		manual = atlas.createSprite("man");

		tracks = atlas.createSprite("tracks");
		motor = atlas.createSprite("motor");
		robot[0] = atlas.createSprite("robot1");
		robot[1] = atlas.createSprite("robot2");
		robotRC[0] = atlas.createSprite("robotrc1");
		robotRC[1] = atlas.createSprite("robotrc2");
		remote = atlas.createSprite("rc");
		radio = atlas.createSprite("radio");
		joystick = atlas.createSprite("joystick");

		bigFlamethrowerBase = atlas.createSprite("bigflamethrowerbase");
		bigFlamethrower = atlas.createSprite("bigflamethrower");

		explodingBarrel = atlas.createSprite("explodingbarrel");

		destWall = atlas.createSprite("destwall");

		safetyUnpressed = atlas.createSprite("safety1");
		safetyPressed = atlas.createSprite("safety2");

		tramEntrance = atlas.createSprite("tram");

		tank = atlas.createSprite("tank");
		tankBroken = atlas.createSprite("tankbroken");

		footprint = atlas.createSprite("footprint");

		airTank = atlas.createSprite("airtank");

		core = atlas.createSprite("core");

		bed = atlas.createSprite("bed");
		cabinets = atlas.createSprite("cabinets");
		hardwareShelf = atlas.createSprite("hardwareshelf");

		device = atlas.createSprite("device");
	}

	Sprites() {
	}
}
