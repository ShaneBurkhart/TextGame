package com.donkka.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.donkka.art.Art;
import com.donkka.helpers.Dimensions;
import com.donkka.sprites.buttons.BackButton;
import com.donkka.sprites.buttons.EnterUsernameButton;
import com.donkka.text.TouchEvent;
import com.donkka.ui.ShaneTextField;
import com.donkka.ui.ShaneTextFieldStyle;

public class EnterUsernameScreen extends ShaneScreen{
	
	private Stage stage;
	private TextField textField;
	private EnterUsernameButton emailUsernameButton;
	private BackButton backButton;
	
	public EnterUsernameScreen(String email){
		this.stage = new Stage();
		this.stage.setCamera(this.camera);
		float textWidth = 400;
		this.textField = new ShaneTextField("Enter a username", new ShaneTextFieldStyle(), new Vector2(Dimensions.getWidth() / 2 - textWidth / 2, 600), textWidth);
		stage.addActor(textField);
		this.emailUsernameButton = new EnterUsernameButton(new Vector2(Dimensions.getTargetWidth() / 2 - Art.loginEmail.getWidth() / 2, 500), Art.loginEmail, textField, this);
		this.backButton = new BackButton(10, 10, Dimensions.Floats.LEFT, Art.back, new WelcomeScreen());
	}

	@Override
	public void show() {
		super.show();
		InputMultiplexer input = new InputMultiplexer(this, stage);
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		emailUsernameButton.render(batch);
		backButton.render(batch);
		batch.end();
		
		stage.act();
		stage.draw();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		emailUsernameButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		backButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		emailUsernameButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		backButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		emailUsernameButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		backButton.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		return false;
	}
}
