package com.donkka.dialog.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.donkka.helpers.GameManager;
import com.donkka.screens.ShaneScreen;

public class PreGameDialogButton extends DialogButton{
	
	ShaneScreen game;
	
	public PreGameDialogButton(Sprite sprite, ShaneScreen game) {
		super(sprite);
		this.game = game;
	}

	@Override
	public void onTouchDown(float x, float y) {}

	@Override
	public void onTouchDragged(float x, float y) {}

	@Override
	public void onTouchUp(float x, float y) {
		GameManager.getInstance().setScreen(game);
	}

}
