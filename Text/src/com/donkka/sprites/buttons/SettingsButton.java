package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.donkka.helpers.GameManager;
import com.donkka.screens.MainMenu;
import com.donkka.transitions.ExitUpTransitionScreen;

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
		GameManager.getInstance().setScreen(new ExitUpTransitionScreen(GameManager.getInstance().getGame().getScreen(), new MainMenu()));
	}
	
}
