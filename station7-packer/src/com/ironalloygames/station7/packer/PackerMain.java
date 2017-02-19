package com.ironalloygames.station7.packer;

import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class PackerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Settings set = new Settings();
		
		TexturePacker.process(set, "./unpacked_assets/", "../station7-android/assets/");
	}
}
