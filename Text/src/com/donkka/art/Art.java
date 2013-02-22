package com.donkka.art;

import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.donkka.debug.DBug;

public class Art {
	
	private static AssetManager manager;

	private static TextureAtlas taBackground;
	public static Sprite background;
	
	private static TextureAtlas taPatches;
	public static Sprite highScorePatch, recentlyPlayedPatch;
	
	private static TextureAtlas taTiles;
	public static Sprite[] largeTiles, tiles;
	
	private static TextureAtlas taButtons;
	public static Sprite play, playTouched, settings;
	
	
	public static void init(){
		manager = new AssetManager();
		manager.load("background.txt", TextureAtlas.class);
		manager.load("patches.txt", TextureAtlas.class);
		manager.load("tiles.txt", TextureAtlas.class);
		manager.load("buttons.txt", TextureAtlas.class);
		
		DBug.print("Manager Initialized...");
	}
	
	public static boolean load(){
		boolean loaded = manager.update();
		
		if(manager.isLoaded("background.txt"))
			loadBackground();
		
		if(manager.isLoaded("patches.txt"))
			loadPatches();
		
		if(manager.isLoaded("tiles.txt"))
			loadTiles();

		if(manager.isLoaded("buttons.txt"))
			loadButtons();
		
		return loaded;
	}
	
	private static void loadButtons(){
		taButtons = manager.get("buttons.txt", TextureAtlas.class);
		play = new Sprite(taButtons.createSprite("play"));
		settings = taButtons.createSprite("settings");
	}
	
	private static void loadTiles(){
		largeTiles = new Sprite[9];
		char[] letters = {'V', 'P', 'M', 'T', 'L', 'B', 'S', 'E', 'A'};
		taTiles = manager.get("tiles.txt", TextureAtlas.class);
		for(int i = 0 ; i < letters.length ; i ++)
			largeTiles[i] = taTiles.createSprite(letters[i] + "");
		tiles = new Sprite[26];
		char c = 'a';
		for(int i = 0 ; i < tiles.length ; i ++){
			tiles[i] = taTiles.createSprite(c + "");
			c++;
		}
	}
	
	private static void loadBackground(){
		taBackground = manager.get("background.txt", TextureAtlas.class);
		background = taBackground.createSprite("background");
	}
	
	private static void loadPatches(){
		taPatches = manager.get("patches.txt", TextureAtlas.class);
		highScorePatch = taPatches.createSprite("highscorepatch");
		recentlyPlayedPatch = taPatches.createSprite("recent");
	}
	
	private static Random rand = new Random();
	
	public static Sprite getRandomLargeTile(){
		return largeTiles[rand.nextInt(9)];
	}
	
	public static Sprite getTile(char cLower){
		return tiles[cLower - 'a'];
	}
}
