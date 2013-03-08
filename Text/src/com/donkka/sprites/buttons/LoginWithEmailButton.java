package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.donkka.helpers.GameManager;
import com.donkka.screens.EnterEmailScreen;
import com.donkka.transitions.ExitLeftTransitionScreen;

public class LoginWithEmailButton extends ShaneButton{

	public LoginWithEmailButton(Vector2 pos, Sprite sprite) {
		super(pos, sprite);
	}

	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		GameManager.getInstance().setScreen(new ExitLeftTransitionScreen(GameManager.getInstance().getGame().getScreen(), new EnterEmailScreen()));
		//GameManager.getInstance().setScreen(new ExitLeftTransitionScreen(GameManager.getInstance().getGame().getScreen(), new MainMenu()));
	}
	
}
