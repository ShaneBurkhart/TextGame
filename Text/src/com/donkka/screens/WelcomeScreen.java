package com.donkka.screens;

import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.helpers.Dimensions;
import com.donkka.sprites.buttons.LoginWithEmailButton;
import com.donkka.sprites.buttons.LoginWithFacebookButton;
import com.donkka.text.TouchEvent;


public class WelcomeScreen extends ShaneScreen{

	LoginWithFacebookButton loginWithFacebook;
	LoginWithEmailButton loginWithEmail;
	
	public WelcomeScreen(){
		loginWithFacebook = new LoginWithFacebookButton(new Vector2(Dimensions.getWidth() / 2 - Art.loginFacebook.getWidth() / 2, 500), Art.loginFacebook);
		loginWithEmail = new LoginWithEmailButton(new Vector2(Dimensions.getWidth() / 2 - Art.loginFacebook.getWidth() / 2, 300), Art.loginEmail);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		loginWithFacebook.render(batch);
		loginWithEmail.render(batch);
		batch.end();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		loginWithEmail.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		loginWithFacebook.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		loginWithEmail.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		loginWithFacebook.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		loginWithEmail.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		loginWithFacebook.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		return false;
	}
}
