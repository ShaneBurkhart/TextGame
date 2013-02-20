package com.donkka.text;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.donkka.art.Art;
import com.donkka.helpers.Dictionary;
import com.donkka.helpers.Dimensions;
import com.donkka.helpers.GameManager;
import com.donkka.screens.MainMenu;
import com.donkka.screens.ParentGameScreen;
import com.donkka.transitions.FadeTransitionScreen;

public class TextGame extends Game {

	@Override
	public void create() {
		//Init Dictionary
		Dictionary.getInstance();
		
		//Init Dimensions
		Dimensions.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Init Graphics.  Later will do this in an asynchronous task so not to slow down
		Art.init();
		
		//Init GameManager for retrieval of game statically
		GameManager.getInstance().init(new ParentGameScreen());
		
		//Set Screen in with game manager
		GameManager.getInstance().setScreen(new FadeTransitionScreen(null, new MainMenu()));
		
		//Add parent game manager to game
		setScreen(GameManager.getInstance().getGame());
	}
}
