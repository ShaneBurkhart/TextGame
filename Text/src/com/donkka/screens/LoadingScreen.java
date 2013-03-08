package com.donkka.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.donkka.art.Art;
import com.donkka.dialog.LoadingDialog;
import com.donkka.dialog.callback.FetchGameDataCallback;
import com.donkka.helpers.GameManager;
import com.donkka.player.UserCredentials;

public class LoadingScreen implements Screen{

	Game game;
	ShapeRenderer shapes = new ShapeRenderer();
	
	public LoadingScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		if(Art.load()){
			//Init all screens for faster loading
			//GameManager.getInstance().initScreens();
			
			//Set Screen in with game manager
			if(UserCredentials.getInstance().isFacebook() && UserCredentials.getInstance().getEmail() != ""){
				
			}else if(UserCredentials.getInstance().getEmail() != "" && UserCredentials.getInstance().getPassword() != ""){
				GameManager.getInstance().setScreen(new LoadingDialog(new ShaneScreen(), new FetchGameDataCallback()));
			}else{
				GameManager.getInstance().setScreen(new WelcomeScreen());
			}
			
			//Add parent game manager to game
			game.setScreen(GameManager.getInstance().getGame());
		}
		shapes.begin(ShapeType.Filled);
		shapes.setColor(Color.BLUE);
		shapes.rect(0, 0, 100, 100);
		shapes.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
