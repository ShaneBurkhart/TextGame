package com.donkka.art;

import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.donkka.debug.DBug;

public class Art {
	
	private static AssetManager manager;

	private static TextureAtlas taBackground;
	public static Sprite background;
	
	private static TextureAtlas taPatches;
	public static Sprite highScorePatch, recentlyPlayedPatch, orangePatch, greenPatch, dividerPatch, dialogPatch, textFieldPatch,
						cursorPatch, selectionPatch;
	
	private static TextureAtlas taTiles;
	public static Sprite[] largeTiles, tiles;
	public static Sprite empty;
	
	private static TextureAtlas taButtons;
	public static Sprite play, playTouched, settings, submit, recall, shuffle, letsgo, loginFacebook, loginEmail, back;
	
	private static TextureAtlas taLoadingCircle;
	public static ShaneAnimation loadingCircle;
	
	public static BitmapFont arial40, calibri22, calibri40;
	
	
	public static void init(){
		manager = new AssetManager();
		manager.load("background.txt", TextureAtlas.class);
		manager.load("patches.txt", TextureAtlas.class);
		manager.load("tiles.txt", TextureAtlas.class);
		manager.load("buttons.txt", TextureAtlas.class);
		manager.load("loadingCircle.txt", TextureAtlas.class);
		manager.load("fonts/40arial.fnt", BitmapFont.class);
		manager.load("fonts/40calibri.fnt", BitmapFont.class);
		manager.load("fonts/22calibri.fnt", BitmapFont.class);
		
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
		
		if(manager.isLoaded("loadingCircle.txt"))
			loadLoadingCircleAnimation();
		
		if(manager.isLoaded("fonts/40arial.fnt")){
			arial40 = manager.get("fonts/40arial.fnt");
			arial40.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		if(manager.isLoaded("fonts/22calibri.fnt")){
			calibri22 = manager.get("fonts/22calibri.fnt");
			calibri22.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		if(manager.isLoaded("fonts/40calibri.fnt")){
			calibri40 = manager.get("fonts/40calibri.fnt");
			calibri40.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		return loaded;
	}
	
	private static void loadLoadingCircleAnimation(){
		taLoadingCircle = manager.get("loadingCircle.txt", TextureAtlas.class);
		loadingCircle = new ShaneAnimation(new Animation(.025f, taLoadingCircle.createSprites()));
	}
	
	private static void loadButtons(){
		taButtons = manager.get("buttons.txt", TextureAtlas.class);
		play = new Sprite(taButtons.createSprite("play"));
		linearize(play);
		settings = taButtons.createSprite("settings");
		linearize(settings);
		shuffle = taButtons.createSprite("shuffle");
		linearize(shuffle);
		recall = taButtons.createSprite("recall");
		linearize(recall);
		submit = taButtons.createSprite("submit");
		linearize(submit);
		letsgo = taButtons.createSprite("letsgo");
		linearize(letsgo);
		loginFacebook = taButtons.createSprite("loginfacebook");
		linearize(loginFacebook);
		loginEmail = taButtons.createSprite("loginemail");
		linearize(loginEmail);
		back = taButtons.createSprite("back");
		linearize(back);
	}
	
	private static void loadTiles(){
		largeTiles = new Sprite[9];
		char[] letters = {'V', 'P', 'M', 'T', 'L', 'B', 'S', 'E', 'A'};
		taTiles = manager.get("tiles.txt", TextureAtlas.class);
		for(int i = 0 ; i < letters.length ; i ++){
			largeTiles[i] = taTiles.createSprite(letters[i] + "");
			linearize(largeTiles[i]);
		}
		tiles = new Sprite[26];
		char c = 'a';
		for(int i = 0 ; i < tiles.length ; i ++){
			tiles[i] = taTiles.createSprite(c + "");
			linearize(tiles[i]);
			c++;
		}
		empty = taTiles.createSprite("empty");
		linearize(empty);
	}
	
	private static void loadBackground(){
		taBackground = manager.get("background.txt", TextureAtlas.class);
		background = taBackground.createSprite("background");
	}
	
	private static void loadPatches(){
		taPatches = manager.get("patches.txt", TextureAtlas.class);
		highScorePatch = taPatches.createSprite("highscorepatch");
		linearize(highScorePatch);
		recentlyPlayedPatch = taPatches.createSprite("recent");
		linearize(recentlyPlayedPatch);
		greenPatch = taPatches.createSprite("green");
		linearize(greenPatch);
		orangePatch = taPatches.createSprite("orange");
		linearize(orangePatch);
		dividerPatch = taPatches.createSprite("divider");
		linearize(dividerPatch);
		dialogPatch = taPatches.createSprite("dialog");
		linearize(dialogPatch);
		textFieldPatch = taPatches.createSprite("textfield");
		linearize(textFieldPatch);
		cursorPatch = taPatches.createSprite("cursor");
		linearize(cursorPatch);
		selectionPatch = taPatches.createSprite("selection");
		linearize(selectionPatch);
	}
	
	private static Random rand = new Random();
	
	public static Sprite getRandomLargeTile(){
		return largeTiles[rand.nextInt(9)];
	}
	
	public static Sprite getTile(char cLower){
		return tiles[cLower - 'a'];
	}
	
	public static void linearize(Sprite s){
		s.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
}
