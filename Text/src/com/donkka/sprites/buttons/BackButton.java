package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.donkka.helpers.GameManager;
import com.donkka.screens.ShaneScreen;
import com.donkka.transitions.ExitRightTransitionScreen;

public class BackButton extends FloatingButton{

	private ShaneScreen back;
	
	public BackButton(float xOffset, float yOffset, int dimensionFloat,
			Sprite sprite, ShaneScreen back) {
		super(xOffset, yOffset, dimensionFloat, sprite);
		this.back = back;
	}
	
	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		GameManager.getInstance().setScreen(new ExitRightTransitionScreen(GameManager.getInstance().getGame().getScreen(), back));
	}
	
}
