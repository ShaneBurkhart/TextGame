package com.donkka.screens;

import com.donkka.entities.RecentlyPlayed;

public class GameScreen extends ShaneScreen{
	
	RecentlyPlayed recentlyPlayed;
	
	public GameScreen(){
		recentlyPlayed = new RecentlyPlayed(75, 480);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		recentlyPlayed.render(batch);
		batch.end();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		recentlyPlayed.add("aasdf");
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		
		return false;
	}
}