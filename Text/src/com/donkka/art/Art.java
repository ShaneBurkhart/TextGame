package com.donkka.art;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.donkka.debug.DBug;

public class Art {

	private static TextureAtlas taBackground;
	public static Sprite background;
	
	private static TextureAtlas taPatches;
	public static Sprite highScorePatch, recentlyPlayedPatch;
	
	private static TextureAtlas taTiles;
	public static Sprite[] largeTiles, tiles;
	
	private static TextureAtlas taButtons;
	public static Sprite play, playTouched, settings;
	
	
	public static void init(){
		taBackground = new TextureAtlas(Gdx.files.internal("background.txt"));
		background = taBackground.createSprite("background");
		
		taPatches = new TextureAtlas(Gdx.files.internal("patches.txt"));
		highScorePatch = taPatches.createSprite("highscorepatch");
		recentlyPlayedPatch = taPatches.createSprite("recent");
		
		largeTiles = new Sprite[9];
		char[] letters = {'V', 'P', 'M', 'T', 'L', 'B', 'S', 'E', 'A'};
		taTiles = new TextureAtlas(Gdx.files.internal("tiles.txt"));
		for(int i = 0 ; i < letters.length ; i ++)
			largeTiles[i] = taTiles.createSprite(letters[i] + "");
		tiles = new Sprite[26];
		char c = 'a';
		for(int i = 0 ; i < tiles.length ; i ++){
			tiles[i] = taTiles.createSprite(c + "");
			c++;
		}
		
		taButtons = new TextureAtlas(Gdx.files.internal("buttons.txt"));
		play = new Sprite(taButtons.createSprite("play"));
		settings = taButtons.createSprite("settings");
		
		DBug.print("Art Initialized...");
	}
	
	private static Random rand = new Random();
	
	public static Sprite getRandomLargeTile(){
		return largeTiles[rand.nextInt(9)];
	}
	
	public static Sprite getTile(char cLower){
		return tiles[cLower - 'a'];
	}
}
