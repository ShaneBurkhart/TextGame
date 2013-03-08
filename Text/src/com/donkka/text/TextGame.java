package com.donkka.text;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.donkka.art.Art;
import com.donkka.facebook.FaceBookInterface;
import com.donkka.helpers.Dictionary;
import com.donkka.helpers.Dimensions;
import com.donkka.helpers.GameManager;
import com.donkka.player.UserCredentials;
import com.donkka.screens.LoadingScreen;
import com.donkka.screens.ParentGameScreen;

public class TextGame extends Game {
	
	@Override
	public void pause() {
		UserCredentials.getInstance().saveCredentials();
		super.pause();
	}

	@Override
	public void resume() {
		UserCredentials.getInstance().loadCredentials();
		super.resume();
	}

	FaceBookInterface fbInterface;
	
	public TextGame(FaceBookInterface fbInterface){
		this.fbInterface = fbInterface;
	}

	@Override
	public void create() {
		//Init Dictionary
		Dictionary.getInstance();
		
		//Init Dimensions
		Dimensions.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Init Graphics.  Prepares asset Manager for async loading screen load
		Art.init();
		
		//Load User Credentials
		UserCredentials.getInstance().clear();
		
		//Init GameManager for retrieval of game statically
		GameManager.getInstance().init(new ParentGameScreen());
		
		//Add parent game manager to game
		setScreen(new LoadingScreen(this));
	}
}
