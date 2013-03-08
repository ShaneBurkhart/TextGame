package com.donkka.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.donkka.art.Art;
import com.donkka.entities.BackgroundLetterGenerator;
import com.donkka.entities.GameInfo;
import com.donkka.entities.menu.GameMenuInterface;
import com.donkka.entities.menu.YourTurnItem;
import com.donkka.helpers.Dimensions;
import com.donkka.sprites.buttons.SettingsButton;
import com.donkka.text.TouchEvent;


public class MainMenu extends ShaneScreen{
	
	private static final float BOTTOM_MARGIN = 10;
	private OrthographicCamera letterCamera;
	
	BackgroundLetterGenerator letters;
	SettingsButton settingsButton;
	GameMenuInterface menuInterface;
	
	public MainMenu(){
		letters = new BackgroundLetterGenerator();
		settingsButton = new SettingsButton(10, 10, Dimensions.Floats.LEFT, Art.settings);
		menuInterface = new GameMenuInterface(700);
		menuInterface.addToYourMove(new YourTurnItem(new GameInfo(10, 100, 110, "asdf", "asdflkj")));
		menuInterface.addToYourMove(new YourTurnItem(new GameInfo(10, 100, 110, "asdf", "dsasdD")));
		lastTouchedScreen = new Vector3();
		letterCamera = new OrthographicCamera(Dimensions.getWidth(), Dimensions.getHeight());
		letterCamera.position.set(Dimensions.getWidth() / 2, Dimensions.getHeight() / 2, 0);
		letterCamera.update();
		letterCamera.apply(Gdx.gl10);
	}

	@Override
	public void setCameraPosY(float y) {
		super.setCameraPosY(y);
		letterCamera.position.y = y;
		letterCamera.update();
	}

	@Override
	public void setCameraPosX(float x) {
		super.setCameraPosX(x);
		letterCamera.position.x = x;
		letterCamera.update();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		letterCamera.viewportWidth = Dimensions.getWidth();
		letterCamera.viewportHeight = Dimensions.getHeight();
		letterCamera.update();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		letterCamera.update();
		batch.setProjectionMatrix(letterCamera.combined);
		letters.update(delta, camera);
		letters.render(batch);
		batch.setProjectionMatrix(camera.combined);
		settingsButton.render(batch);
		menuInterface.render(batch, delta);
		batch.end();
	}
	
	private Vector3 lastTouchedScreen;
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		menuInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		lastTouchedScreen.set(screenX, screenY, 0);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		menuInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		settingsButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		menuInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		moveCamera(screenY);
		lastTouchedScreen.set(screenX, screenY, 0);
		return false;
	}
	
	private void moveCamera(float screenY){
		float dy = (screenY - lastTouchedScreen.y) * Dimensions.getHeight() / Gdx.graphics.getHeight();
		camera.position.set(camera.position.x, Math.min(Math.max(menuInterface.getTop() - menuInterface.getHeight() - BOTTOM_MARGIN + Dimensions.getHeight() / 2 ,camera.position.y + dy), Dimensions.getHeight() / 2), 0);
	}
}
