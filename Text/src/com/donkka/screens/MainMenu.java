package com.donkka.screens;

import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.entities.BackgroundLetterGenerator;
import com.donkka.helpers.Dimensions;
import com.donkka.patches.HighScoreNinePatch;
import com.donkka.sprites.DoubleFloatingNinePatch;
import com.donkka.sprites.FloatingNinePatch;
import com.donkka.sprites.buttons.PlayButton;
import com.donkka.sprites.buttons.SettingsButton;
import com.donkka.text.TouchEvent;


public class MainMenu extends ShaneScreen{
	
	BackgroundLetterGenerator letters;
	PlayButton playButton;
	SettingsButton settingsButton;
	FloatingNinePatch highScoreBackground;
	DoubleFloatingNinePatch nine;
	
	public MainMenu(){
		letters = new BackgroundLetterGenerator();
		playButton = new PlayButton(new Vector2(240 - Art.play.getWidth() / 2, 400 - Art.play.getHeight() / 2), Art.play);
		settingsButton = new SettingsButton(20, 20, Dimensions.Floats.LEFT, Art.settings);
		highScoreBackground = new FloatingNinePatch(20, 0, 190, 100, Dimensions.Floats.RIGHT, HighScoreNinePatch.getInstance());
		nine = new DoubleFloatingNinePatch(20, 200, 300, HighScoreNinePatch.getInstance());
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		batch.begin();
		letters.update(delta, camera);
		letters.render(batch);
		playButton.render(batch);
		settingsButton.render(batch);
		highScoreBackground.render(batch);
		nine.render(batch);
		batch.end();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		playButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		playButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		playButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		return false;
	}
}
