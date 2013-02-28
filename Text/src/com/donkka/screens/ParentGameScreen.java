package com.donkka.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.donkka.entities.Background;
import com.donkka.text.Timing;

public class ParentGameScreen implements Screen{

	ShaneScreen screen;
	Background background;
	
	public void setScreen(ShaneScreen screen){
		this.screen = screen;
		this.screen.show();
	}
	
	public ShaneScreen getScreen(){
		return screen;
	}
	
	public ParentGameScreen(){
		this.background = new Background();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(delta > Timing.MAX_DELTA)
			delta = Timing.MAX_DELTA;
		
		background.render(delta);
		screen.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		screen.resize(width, height);
		background.resize(width, height);
	}

	@Override
	public void show() {
		screen.show();
		background.show();
	}

	@Override
	public void hide() {
		screen.hide();
	}

	@Override
	public void pause() {
		screen.pause();
	}

	@Override
	public void resume() {
		screen.resume();
	}

	@Override
	public void dispose() {
		screen.dispose();
	}

}
