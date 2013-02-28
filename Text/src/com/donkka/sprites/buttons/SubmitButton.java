package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.donkka.entities.ButtonPanel;

public class SubmitButton extends ShaneButton{
	
	ButtonPanel buttonPanel;

	public SubmitButton(Vector2 pos, Sprite sprite, ButtonPanel buttonPanel) {
		super(pos, sprite);
		this.buttonPanel = buttonPanel;
	}

	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		buttonPanel.getTileInterface().submit();
	}
	
}
