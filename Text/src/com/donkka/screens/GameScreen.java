package com.donkka.screens;

import com.donkka.entities.RecentlyPlayed;
import com.donkka.entities.TileInterface;
import com.donkka.text.TouchEvent;

public class GameScreen extends ShaneScreen{
	
	RecentlyPlayed recentlyPlayed;
	TileInterface tileInterface;
	
	public GameScreen(){
		recentlyPlayed = new RecentlyPlayed(75, 480);
		tileInterface = new TileInterface(new char[] {'p', 'b', 'c', 'z', 's', 't', 'm'});
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		recentlyPlayed.render(batch);
		tileInterface.render(batch, delta);
		batch.end();
	}
	boolean tick = false;

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		if(tick)
			recentlyPlayed.add("tick");
		else
			recentlyPlayed.add("passes");
		tick = !tick;
		tileInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		tileInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		tileInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		return false;
	}
}
