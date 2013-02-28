package com.donkka.helpers;

import com.donkka.debug.DBug;
import com.donkka.screens.MainMenu;
import com.donkka.screens.ParentGameScreen;
import com.donkka.screens.ShaneScreen;


public class GameManager {
	
	ParentGameScreen game = null;
	private static GameManager instance;
	
	private MainMenu mainMenu;
	
	private GameManager(){
	}
	
	public static GameManager getInstance(){
		if(instance == null)
			instance = new GameManager();
		return instance;
	}
	
	public ParentGameScreen getGame(){
		return game;
	}
	
	public void init(ParentGameScreen game){
		this.game = game;
		DBug.print("GameManager Initialized...");
	}
	
	public void setScreen(ShaneScreen screen){
		this.game.setScreen(screen);
	}
	
	public void initScreens(){
		this.mainMenu = new MainMenu();
	}
	
	public MainMenu getMainMenu(){
		return mainMenu;
	}
}
