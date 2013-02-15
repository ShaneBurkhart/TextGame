package com.donkka.screens;

import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.buttons.PlayButton;
import com.donkka.buttons.SettingsButton;
import com.donkka.entities.BackgroundLetterGenerator;
import com.donkka.patches.HighScoreNinePatch;
import com.donkka.text.TouchEvent;


public class MainMenu extends ShaneScreen{
	
	BackgroundLetterGenerator letters;
	PlayButton playButton;
	//SettingsButton settingsButton;
	
	public MainMenu(){
		letters = new BackgroundLetterGenerator();
		playButton = new PlayButton(new Vector2(240 - Art.play.getWidth() / 2, 400 - Art.play.getHeight() / 2), Art.play);
		//settingsButton = new SettingsButton(new Vector2(20, camera.position.y + camera.viewportHeight / 2 - 20), Art.settings);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		batch.begin();
		letters.update(delta, camera);
		letters.render(batch);
		playButton.render(batch);
		//settingsButton.render(batch);
		HighScoreNinePatch.getInstance().draw(batch, 480 - 190 - 20, camera.position.y + camera.viewportHeight / 2 - 100, 190, 100);
		batch.end();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		playButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		//settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		playButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		//settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		playButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		//settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		return false;
	}
}
