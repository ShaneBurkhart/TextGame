package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.donkka.helpers.GameManager;
import com.donkka.screens.GameScreen;
import com.donkka.transitions.ExitLeftTransitionScreen;

public class SettingsButton extends FloatingButton{

	public SettingsButton(float xOffset, float yOffset, int dimensionFloat, Sprite sprite) {
		super(xOffset, yOffset, dimensionFloat, sprite);
	}

	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		GameManager.getInstance().setScreen(new ExitLeftTransitionScreen(GameManager.getInstance().getGame().getScreen(), new GameScreen()));
	}
	
}
