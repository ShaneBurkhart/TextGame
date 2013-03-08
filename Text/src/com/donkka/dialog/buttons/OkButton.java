package com.donkka.dialog.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.donkka.helpers.GameManager;
import com.donkka.screens.ShaneScreen;

public class OkButton extends DialogButton{

	private ShaneScreen screen;
	
	public OkButton(Sprite sprite, ShaneScreen screen) {
		super(sprite);
		this.screen = screen;
	}

	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		GameManager.getInstance().setScreen(screen);
	}
	
}
