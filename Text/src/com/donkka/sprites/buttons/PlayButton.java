package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.donkka.helpers.GameManager;
import com.donkka.screens.GameScreen;
import com.donkka.transitions.ExitLeftTransitionScreen;

public class PlayButton extends ShaneButton{

	public PlayButton(Vector2 pos, Sprite sprite) {
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
		GameManager.getInstance().setScreen(new ExitLeftTransitionScreen(GameManager.getInstance().getGame().getScreen(), new GameScreen()));
	}
	
}
