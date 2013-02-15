package com.donkka.helpers;

import com.donkka.screens.ParentGameScreen;
import com.donkka.screens.ShaneScreen;


public class GameManager {
	
	ParentGameScreen game = null;
	private static GameManager instance;
	
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
	}
	
	public void setScreen(ShaneScreen screen){
		this.game.setScreen(screen);
	}
}
